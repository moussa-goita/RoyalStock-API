DELIMITER
$$
CREATE PROCEDURE info_manager_venduer()
BEGIN
SELECT COUNT(*) AS total_manager
FROM roles r
         JOIN utilisateurs u ON r.id = u.roles_id
WHERE r.name = 'MANAGER';
SELECT COUNT(*) AS total_vendeur
FROM roles r
         JOIN utilisateurs u ON r.id = u.roles_id
WHERE r.name = 'VENDEUR';
END$$
DELIMITER ;

CALL info_manager_venduer();

/********************************************************************************************/

DELIMITER
$$
CREATE PROCEDURE info_fournisseur()
BEGIN
SELECT COUNT(*) AS fournisseurs
FROM fournisseurs;
END$$
DELIMITER ;
CALL info_fournisseur();

/********************************************************************************************/

DELIMITER
$$
CREATE PROCEDURE top_vendu()
BEGIN
SELECT p.product_name, p.description, COUNT(p.id) AS nbre
FROM details_sorties d
         JOIN bon_sorties b ON d.bon_sortie_id = b.id
         JOIN produits p ON p.id = d.produit_id
         JOIN motif m ON m.id = b.motif_id
WHERE m.title = 'vente'
GROUP BY p.product_name
ORDER BY nbre DESC LIMIT 5;
END$$
DELIMITER ;
CALL top_vendu();

/********************************************************************************************/

DELIMITER
$$
CREATE PROCEDURE top_entree()
BEGIN
SELECT p.product_name, p.description, COUNT(p.id) AS nbre
FROM details_entrees d
         JOIN bon_entrees b ON b.id = d.bon_entree_id
         JOIN produits p ON p.id = d.produit_id
GROUP BY p.product_name
ORDER BY nbre DESC LIMIT 5;
END$$
DELIMITER ;
CALL top_entree();

/********************************************************************************************/

DELIMITER
$$
CREATE TRIGGER mise_a_jour_produit_apres_entree
    AFTER UPDATE
    ON bon_entrees
    FOR EACH ROW
BEGIN
    UPDATE produits p
        JOIN (
        SELECT d.produit_id, SUM (d.quantite) AS quantite
        FROM details_entrees d
        JOIN bon_entrees b ON d.bon_entree_id = b.id
        WHERE b.statut = 'En Stock'
        GROUP BY d.produit_id
        ) q
    ON p.id = q.produit_id
        SET p.quantity = p.quantity + q.quantite;
    END$$
    DELIMITER ;

/********************************************************************************************/

DELIMITER $$
    CREATE TRIGGER mise_a_jour_produit_apres_sortie
        AFTER UPDATE
        ON bon_entrees
        FOR EACH ROW
    BEGIN
        UPDATE produits p
            JOIN (
            SELECT d.produit_id, SUM (d.quantity) AS quantite
            FROM details_sorties d
            JOIN bon_sorties b ON d.bon_sortie_id = b.id
            JOIN motif m ON m.id = b.motif_id
            WHERE m.title = 'vente'
            GROUP BY d.produit_id
            ) q
        ON p.id = q.produit_id
            SET p.quantity = p.quantity - q.quantite;
        END$$
        DELIMITER ;

/********************************************************************************************/

DELIMITER $$

        CREATE TRIGGER mise_a_jour_produit_apres_sortie
            AFTER UPDATE
            ON bon_entrees
            FOR EACH ROW
        BEGIN

            UPDATE produits p
                JOIN (
                SELECT d.produit_id, SUM (d.quantity) AS quantite
                FROM details_sorties d
                JOIN bon_sorties b ON d.bon_sortie_id = b.id
                JOIN motif m ON m.id = b.motif_id
                WHERE m.title = 'VENTE'
                GROUP BY d.produit_id
                ) q
            ON p.id = q.produit_id
                SET p.quantity = p.quantity - q.quantite;

            END$$
            DELIMITER ;

/********************************************************************************************/

DELIMITER $$

            CREATE TRIGGER alerte_stock_bas
                AFTER UPDATE
                ON produits
                FOR EACH ROW
            BEGIN
                DECLARE utilisateur_id INT;

    -- Récupère l'utilisateur associé au bon d'entrée le plus récent pour le produit mis à jour
                SELECT be.utilisateur_id
                INTO utilisateur_id
                FROM details_entrees de
                         JOIN bon_entrees be ON de.bon_entree_id = be.id
                WHERE de.produit_id = NEW.id -- assurez-vous que NEW.id est le bon champ
                ORDER BY be.date_commande DESC LIMIT 1;

                -- Vérifie si la quantité est inférieure au seuil et que la nouvelle quantité est différente de l'ancienne
                IF NEW.quantity < 10 AND NEW.quantity <> OLD.quantity THEN
        -- Insère la notification dans la table des notifications
        INSERT INTO notifications (contenu, date_notif, is_read, utilisateur_id)
        VALUES (
            CONCAT('Alerte : Le stock du produit ', NEW.product_name, ' est bas. Quantité actuelle : ', NEW.quantity),
            NOW(), "false", utilisateur_id
        );
            END IF;
        END $$

DELIMITER ;
