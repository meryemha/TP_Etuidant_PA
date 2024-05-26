package com.example.etudiant_tp.service;

import com.example.etudiant_tp.model.Departement;
import com.example.etudiant_tp.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Departement updateDepartement(Integer id, Departement departement) {
        return departementRepository.save(departement);
    }

    public void deleteDepartement(Integer id) {
        departementRepository.deleteById(id);
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Optional<Departement> getDepartementById(Integer id) {
        return departementRepository.findById(id);
    }
}
