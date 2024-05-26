package com.example.etudiant_tp.service;

import com.example.etudiant_tp.model.Departement;
import com.example.etudiant_tp.model.Universite;
import com.example.etudiant_tp.repository.DepartementRepository;
import com.example.etudiant_tp.repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UniversiteService {

    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private DepartementRepository departementRepository;

    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    public Universite updateUniversite(Integer id, Universite universiteDetails) {
        Optional<Universite> universiteOptional = universiteRepository.findById(id);
        if (universiteOptional.isPresent()) {
            Universite universite = universiteOptional.get();
            universite.setNomUniv(universiteDetails.getNomUniv());
            return universiteRepository.save(universite);
        }
        return null;
    }

    public void deleteUniversite(Integer id) {
        universiteRepository.deleteById(id);
    }

    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    public Optional<Universite> getUniversiteById(Integer id) {
        return universiteRepository.findById(id);
    }

    public Universite addDepartementToUniversite(Integer universiteId, Integer departementId) {
        Optional<Universite> universiteOptional = universiteRepository.findById(universiteId);
        if (universiteOptional.isPresent()) {
            Universite universite = universiteOptional.get();
            Optional<Departement> departementOptional = departementRepository.findById(departementId);
            if (departementOptional.isPresent()) {
                Departement departement = departementOptional.get();
                universite.getDepartements().add(departement);
                departement.setUniversite(universite); // Set the university in the department
                return universiteRepository.save(universite);
            } else {
                // Handle case where department is not found
                return null;
            }
        } else {
            // Handle case where university is not found
            return null;
        }
    }

    public List<Departement> getAllDepartementsByUniversiteId(Integer universiteId) {
        Optional<Universite> universiteOptional = universiteRepository.findById(universiteId);
        if (universiteOptional.isPresent()) {
            Universite universite = universiteOptional.get();
            return new ArrayList<>(universite.getDepartements());
        } else {
            return Collections.emptyList();
        }
    }


}
