package com.test.controllers;

import com.test.entities.RapportRequest;
import com.test.services.RapportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rapports")
public class RapportController {

    @Autowired
    private RapportService rapportService;

    @GetMapping("/daily")
    public ResponseEntity<byte[]> generateDailyReport() {
        byte[] reportData = rapportService.generateDailyReport();
        if (reportData == null || reportData.length == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(reportData);
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateReport(@RequestBody RapportRequest request) {
        byte[] reportData = rapportService.generateReport(request);
        if (reportData == null || reportData.length == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(reportData);
    }
}