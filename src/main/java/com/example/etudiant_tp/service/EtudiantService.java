package com.example.etudiant_tp.service;

import com.example.etudiant_tp.model.Contract;
import com.example.etudiant_tp.model.Etudiant;
import com.example.etudiant_tp.model.Equipe;
import com.example.etudiant_tp.repository.ContractRepository;
import com.example.etudiant_tp.repository.EtudiantRepository;
import com.example.etudiant_tp.repository.EquipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Integer id, Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Integer id) {
        etudiantRepository.deleteById(id);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Integer id) {
        return etudiantRepository.findById(id);
    }

    public Set<Equipe> getEquipesOfEtudiant(Integer etudiantId) {
        return etudiantRepository.findById(etudiantId).map(Etudiant::getEquipes).orElse(null);
    }

    public Etudiant addEquipeToEtudiant(Integer etudiantId, Equipe equipe) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        if (etudiant.isPresent()) {
            etudiant.get().getEquipes().add(equipe);
            return etudiantRepository.save(etudiant.get());
        }
        return null;
    }

    public Etudiant addContractToEtudiant(Integer etudiantId, Contract contract) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        if (etudiant.isPresent()) {
            contract.setEtudiant(etudiant.get());
            contractRepository.save(contract);
            return etudiant.get();
        }
        return null;
    }

    public Equipe addEtudiantsToEquipe(List<Integer> etudiantIds, Integer equipeId) {
        Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);
        if (optionalEquipe.isPresent()) {
            Equipe equipe = optionalEquipe.get();
            for (Integer etudiantId : etudiantIds) {
                Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
                if (optionalEtudiant.isPresent()) {
                    Etudiant etudiant = optionalEtudiant.get();
                    equipe.getEtudiants().add(etudiant);
                    etudiant.getEquipes().add(equipe);
                    etudiantRepository.save(etudiant);
                } else {
                    throw new EntityNotFoundException("Etudiant not found with id " + etudiantId);
                }
            }
            return equipeRepository.save(equipe);
        } else {
            throw new EntityNotFoundException("Equipe not found with id " + equipeId);
        }
    }

    public Etudiant assignContractToEtudiant(Integer etudiantId, Integer contractId) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
        Optional<Contract> contractOptional = contractRepository.findById(contractId);

        if (etudiantOptional.isPresent() && contractOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            Contract contract = contractOptional.get();

            if (contract.getEtudiant() != null) {
                throw new IllegalStateException("This contract is already assigned to an etudiant");
            }

            contract.setEtudiant(etudiant);
            etudiant.getContracts().add(contract);

            contractRepository.save(contract);
            return etudiantRepository.save(etudiant);
        } else {
            throw new EntityNotFoundException("Etudiant or Contract not found with provided id");
        }
    }
}
