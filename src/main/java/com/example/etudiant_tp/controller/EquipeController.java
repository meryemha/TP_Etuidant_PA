package com.example.etudiant_tp.controller;

import com.example.etudiant_tp.model.Equipe;
import com.example.etudiant_tp.model.Etudiant;
import com.example.etudiant_tp.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.example.etudiant_tp.repository.EquipeRepository;
import com.example.etudiant_tp.repository.EtudiantRepository;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    @Autowired
    private EquipeService equipeService;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @PostMapping("/add")
    public ResponseEntity<Equipe> addEquipe(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.addEquipe(equipe));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable Integer id, @RequestBody Equipe equipeDetails) {
        return ResponseEntity.ok(equipeService.updateEquipe(id, equipeDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Integer id) {
        equipeService.deleteEquipe(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        return ResponseEntity.ok(equipeService.getAllEquipes());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Equipe>> getEquipeById(@PathVariable Integer id) {
        return ResponseEntity.ok(equipeService.getEquipeById(id));
    }

    @PostMapping("/{equipeId}/etudiants/{etudiantId}")
    public Equipe addEtudiantToEquipe(@PathVariable Integer equipeId, @PathVariable Integer etudiantId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);

        if (optionalEquipe.isPresent() && optionalEtudiant.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            Etudiant etudiant = optionalEtudiant.get();

            equipe.getEtudiants().add(etudiant);
            etudiant.getEquipes().add(equipe);

            etudiantRepository.save(etudiant);
            return equipeRepository.save(equipe);
        } else {
            throw new RuntimeException("Equipe or Etudiant not found");
        }
    }

}
