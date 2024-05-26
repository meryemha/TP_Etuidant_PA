package com.example.etudiant_tp.controller;

import com.example.etudiant_tp.model.Equipe;
import com.example.etudiant_tp.model.Etudiant;
import com.example.etudiant_tp.model.Contract;
import com.example.etudiant_tp.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/add")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        return ResponseEntity.ok(etudiantService.addEtudiant(etudiant));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Integer id, @RequestBody Etudiant etudiantDetails) {
        return ResponseEntity.ok(etudiantService.updateEtudiant(id, etudiantDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Integer id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Etudiant>> getEtudiantById(@PathVariable Integer id) {
        return ResponseEntity.ok(etudiantService.getEtudiantById(id));
    }

    @GetMapping("/equipes/{etudiantId}")
    public ResponseEntity<Set<Equipe>> getEquipesOfEtudiant(@PathVariable Integer etudiantId) {
        return ResponseEntity.ok(etudiantService.getEquipesOfEtudiant(etudiantId));
    }

    @PostMapping("/addEquipe/{etudiantId}")
    public ResponseEntity<Etudiant> addEquipeToEtudiant(@PathVariable Integer etudiantId, @RequestBody Equipe equipe) {
        return ResponseEntity.ok(etudiantService.addEquipeToEtudiant(etudiantId, equipe));
    }

    @PostMapping("/addContract/{etudiantId}")
    public ResponseEntity<Etudiant> addContractToEtudiant(@PathVariable Integer etudiantId, @RequestBody Contract contract) {
        return ResponseEntity.ok(etudiantService.addContractToEtudiant(etudiantId, contract));
    }

    @PostMapping("/addEtudiantsToEquipe/{equipeId}")
    public ResponseEntity<Equipe> addEtudiantsToEquipe(@PathVariable Integer equipeId, @RequestBody List<Integer> etudiantIds) {
        return ResponseEntity.ok(etudiantService.addEtudiantsToEquipe(etudiantIds, equipeId));
    }

    @PostMapping("/{etudiantId}/addContract/{contractId}")
    public ResponseEntity<Etudiant> assignContractToEtudiant(@PathVariable Integer etudiantId, @PathVariable Integer contractId) {
        return ResponseEntity.ok(etudiantService.assignContractToEtudiant(etudiantId, contractId));
    }
}
