package com.test.services;

import com.test.entities.Entrepot;
import com.test.repositories.EntrepotRepository;
import com.test.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntrepotService {

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    public List<Entrepot> findAll() {
        return entrepotRepository.findAll();
    }

    public Optional<Entrepot> findById(int id) {
        return entrepotRepository.findById(id);
    }

    public Entrepot save(Entrepot entrepot) {
        return entrepotRepository.save(entrepot);
    }

    public void deleteById(int id) {
        entrepotRepository.deleteById(id);
    }

    public boolean isAccessible(Entrepot entrepot) {

        ChronoLocalDate currentDate = new ChronoLocalDate() {
            @Override
            public Chronology getChronology() {
                return null;
            }

            @Override
            public int lengthOfMonth() {
                return 0;
            }

            @Override
            public long until(Temporal endExclusive, TemporalUnit unit) {
                return 0;
            }

            @Override
            public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
                return null;
            }

            @Override
            public long getLong(TemporalField field) {
                return 0;
            }
        };
        return entrepot.getAbonnementEnd().isAfter(currentDate);
    }
}
