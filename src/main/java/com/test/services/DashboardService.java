package com.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.test.entities.Produit;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getStockInfo() {
        Map<String, Object> stockInfo = new HashMap<>();

        stockInfo.put("stockTotal", jdbcTemplate.queryForObject("SELECT SUM(quantity) FROM produits", Integer.class));
        stockInfo.put("totalEntrees", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM details_entrees", Integer.class));
        stockInfo.put("valeurEntrees", jdbcTemplate.queryForObject("SELECT SUM(prix * quantite) FROM details_entrees", Integer.class));
        stockInfo.put("totalSorties", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM details_sorties", Integer.class));
        stockInfo.put("valeurSorties", jdbcTemplate.queryForObject("SELECT SUM(prix * quantity) FROM details_sorties", Integer.class));

        return stockInfo;
    }
}