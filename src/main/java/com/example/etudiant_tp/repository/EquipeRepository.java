package com.example.etudiant_tp.repository;

import com.example.etudiant_tp.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
    List<Equipe> findByNomEquipeContaining(String nomEquipe);
}
