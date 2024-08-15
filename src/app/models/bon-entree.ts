import { DetailEntree } from './detail-entree';
import { Fournisseur } from './fournisseur';
import { Utilisateur } from './utilisateur';

export interface BonEntree {
  id: number;
  dateCommande: Date;
  statut: string;
  utilisateur: Utilisateur;
  fournisseur: Fournisseur;
  detailEntrees: DetailEntree[];
}
