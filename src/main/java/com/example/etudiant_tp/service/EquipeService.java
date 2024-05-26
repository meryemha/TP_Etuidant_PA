package com.example.etudiant_tp.service;

import com.example.etudiant_tp.model.Equipe;
import com.example.etudiant_tp.model.Etudiant;
import com.example.etudiant_tp.repository.EquipeRepository;
import com.example.etudiant_tp.repository.EtudiantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Equipe addEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(Integer id, Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public void deleteEquipe(Integer id) {
        equipeRepository.deleteById(id);
    }

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(Integer id) {
        return equipeRepository.findById(id);
    }

    public Equipe addEtudiantToEquipe(Integer equipeId, Integer etudiantId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        if (optionalEquipe.isPresent() && optionalEtudiant.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            Etudiant etudiant = optionalEtudiant.get();
            equipe.getEtudiants().add(etudiant);
            etudiant.getEquipes().add(equipe); // Mettre à jour l'équipe de l'étudiant
            equipeRepository.save(equipe);
            return equipe;
        } else {
            throw new EntityNotFoundException("Equipe or Etudiant not found with ids: " + equipeId + ", " + etudiantId);
        }
    }


}
