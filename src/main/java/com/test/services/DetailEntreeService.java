package com.test.services;

import com.test.entities.DetailEntree;
import com.test.repositories.DetailEntreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailEntreeService {

    @Autowired
    private DetailEntreeRepository detailEntreeRepository;

    public List<DetailEntree> findAll() {
        return detailEntreeRepository.findAll();
    }

    public Optional<DetailEntree> findById(int id) {
        return detailEntreeRepository.findById(id);
    }

    public DetailEntree save(DetailEntree detailsEntree) {
        return detailEntreeRepository.save(detailsEntree);
    }

    public void deleteById(int id) {
        detailEntreeRepository.deleteById(id);
    }
}
