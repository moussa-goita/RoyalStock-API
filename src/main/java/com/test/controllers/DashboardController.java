package com.test.controllers;

import com.test.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stock-info")
    public ResponseEntity<Map<String, Object>> getStockInfo() {
        Map<String, Object> stockInfo = dashboardService.getStockInfo();
        return ResponseEntity.ok(stockInfo);
    }
}
