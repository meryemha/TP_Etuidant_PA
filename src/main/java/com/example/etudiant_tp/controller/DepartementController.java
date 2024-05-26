package com.example.etudiant_tp.controller;

import com.example.etudiant_tp.model.Departement;
import com.example.etudiant_tp.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    private DepartementRepository departementRepository;



    @GetMapping("/get/{id}")
    public Departement getDepartementById(@PathVariable Integer id) {
        return departementRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementRepository.save(departement);
    }

    @PutMapping("/update/{id}")
    public Departement updateDepartement(@PathVariable Integer id, @RequestBody Departement departement) {
        Departement existingDepartement = departementRepository.findById(id).orElse(null);
        if (existingDepartement != null) {
            existingDepartement.setNomDepart(departement.getNomDepart());
            existingDepartement.setUniversite(departement.getUniversite());
            return departementRepository.save(existingDepartement);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartement(@PathVariable Integer id) {
        departementRepository.deleteById(id);
    }
}
