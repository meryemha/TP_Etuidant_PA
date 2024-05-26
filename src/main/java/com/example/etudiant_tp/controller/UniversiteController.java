package com.example.etudiant_tp.controller;

import com.example.etudiant_tp.model.Departement;
import com.example.etudiant_tp.model.Universite;
import com.example.etudiant_tp.service.UniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/universites")
public class UniversiteController {
    @Autowired
    private UniversiteService universiteService;

    @PostMapping("/add")
    public ResponseEntity<Universite> addUniversite(@RequestBody Universite universite) {
        return ResponseEntity.ok(universiteService.addUniversite(universite));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Universite> updateUniversite(@PathVariable Integer id, @RequestBody Universite universiteDetails) {
        return ResponseEntity.ok(universiteService.updateUniversite(id, universiteDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUniversite(@PathVariable Integer id) {
        universiteService.deleteUniversite(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Universite>> getAllUniversites() {
        return ResponseEntity.ok(universiteService.getAllUniversites());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Universite>> getUniversiteById(@PathVariable Integer id) {
        return ResponseEntity.ok(universiteService.getUniversiteById(id));
    }

    @PostMapping("/{universiteId}/addDepartement/{departementId}")
    public ResponseEntity<Universite> addDepartementToUniversite(@PathVariable Integer universiteId, @PathVariable Integer departementId) {
        return ResponseEntity.ok(universiteService.addDepartementToUniversite(universiteId, departementId));
    }

    @GetMapping("/{universiteId}/departements")
    public ResponseEntity<List<Departement>> getAllDepartementsByUniversiteId(@PathVariable Integer universiteId) {
        List<Departement> departements = universiteService.getAllDepartementsByUniversiteId(universiteId);
        if (departements.isEmpty()) {
            return ResponseEntity.notFound().build(); // Ou une autre réponse appropriée
        } else {
            return ResponseEntity.ok(departements);
        }
    }


}
