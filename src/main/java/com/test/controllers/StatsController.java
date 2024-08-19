package com.test.controllers;

import com.test.repositories.BonEntreeRepository;
import com.test.repositories.BonSortieRepository;
import com.test.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @Autowired
    private BonEntreeRepository bonEntreeRepository;

    @Autowired
    private BonSortieRepository bonSortieRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/trends")
    public Map<String, List<Object[]>> getTrends() {
        List<Object[]> entreeTrends = bonEntreeRepository.countByMonth();
        List<Object[]> sortieTrends = bonSortieRepository.countByMonth();

        Map<String, List<Object[]>> trends = new HashMap<>();
        trends.put("entrees", entreeTrends);
        trends.put("sorties", sortieTrends);

        return trends;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Long totalEntrees = bonEntreeRepository.count();
        Long totalSorties = bonSortieRepository.count();

        Map<String, Long> stats = new HashMap<>();
        stats.put("totalEntrees", totalEntrees);
        stats.put("totalSorties", totalSorties);

        return stats;
    }

    @GetMapping("/salesByCategory")
    public List<Object[]> getSalesByCategory() {
        return produitRepository.countByCategory();
    }

}

