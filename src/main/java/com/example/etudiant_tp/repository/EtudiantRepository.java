package com.example.etudiant_tp.repository;

import com.example.etudiant_tp.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    List<Etudiant> findByNomEContaining(String nomE);
}
