package com.example.etudiant_tp.repository;

import com.example.etudiant_tp.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
