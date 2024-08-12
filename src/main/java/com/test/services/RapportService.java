package com.test.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.test.entities.*;
import com.test.repositories.BonEntreeRepository;
import com.test.repositories.BonSortieRepository;
import com.test.repositories.DetailEntreeRepository;
import com.test.repositories.DetailSortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;


@Service
public class RapportService {

    @Autowired
    private BonEntreeRepository bonEntreeRepository;

    @Autowired
    private BonSortieRepository bonSortieRepository;
    @Autowired
    private DetailEntreeRepository detailEntreeRepository;
    @Autowired
    private DetailSortieRepository detailSortieRepository;

    public byte[] generateDailyReport() {
        LocalDate reportDate = LocalDate.now();
        List<DetailEntree> entrees = detailEntreeRepository.findAll();
        List<DetailSortie> sorties = detailSortieRepository.findAll();

        // Créer le rapport PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        try {
            document.add(new Paragraph("Rapport de Stock pour le " + reportDate));
            document.add(new Paragraph("Bons d'entrée :"));
            for (DetailEntree entree : entrees) {

                document.add(new Paragraph("Produit: " + entree.getProduit().getProductName() +
                        ", Quantité: " + entree.getQuantite()));
            }
            document.add(new Paragraph("Bons de sortie :"));
            for (DetailSortie sortie : sorties) {

                document.add(new Paragraph("Produit: " + sortie.getProduit().getProductName() +
                        ", Quantité: " + sortie.getQuantity() +
                        ", Motif: " + sortie.getBonSortie().getMotif()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return baos.toByteArray();
    }

    public byte[] generateReport(RapportRequest request) {
        // Logique pour générer un rapport basé sur RapportRequest
        return new byte[0];
    }
}
