package com.test.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.test.dto.TopEntreeDTO;
import com.test.dto.TopVenduDTO;
import com.test.entities.Produit;
import com.test.repositories.ProduitRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.*;

@Service
public class
ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private NotificationService notificationService;

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Optional<Produit> findById(int id) {
        return produitRepository.findById(id);
    }

    //cette methode nous permets verifier les produits exipiré depuis le lancement de l'appli vue on est pas sur un server
    @PostConstruct
    public void init() {
        verifierProduitsExpirantBientot();
    }

    // Exécution chaque jour à 00h00 et s'il trouve des produits proches a l'expiration automatiquement la notif est creer
    @Scheduled(cron = "0 0 0 * * ?")
    public void verifierProduitsExpirantBientot() {
        LocalDate dansDeuxSemaines = LocalDate.now().plusWeeks(2);
        List<Produit> produitsExpirantBientot = produitRepository.findAllByDateExpirationBefore(dansDeuxSemaines);

        for (Produit produit : produitsExpirantBientot) {
            notificationService.envoyerNotificationExpiration(produit);
        }
    }
 public Produit save(Produit produit) {
        String qrCodeText = produit.getProductName() + " - " + produit.getId();
        produit.setQrCode(generateQRCode(qrCodeText));
        return produitRepository.save(produit);
    }

    private String generateQRCode(String text) {
        try {
            int size = 250;
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size, hints);

            BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            byte[] qrImageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(qrImageBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
    public void deleteById(int id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> findByEntrepotId(int entrepotId) {
        return produitRepository.findByEntrepotId(entrepotId);
    }
}
