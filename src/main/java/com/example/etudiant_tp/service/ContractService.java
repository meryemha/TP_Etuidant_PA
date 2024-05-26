package com.example.etudiant_tp.service;

import com.example.etudiant_tp.model.Contract;
import com.example.etudiant_tp.repository.ContractRepository;
import com.example.etudiant_tp.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract updateContract(Integer id, Contract contractDetails) {
        Optional<Contract> contractOptional = contractRepository.findById(id);
        if (contractOptional.isPresent()) {
            Contract contract = contractOptional.get();
            contract.setDateDebutContrat(contractDetails.getDateDebutContrat());
            contract.setDateFinContrat(contractDetails.getDateFinContrat());
            contract.setSpecialite(contractDetails.getSpecialite());
            contract.setArchive(contractDetails.getArchive());
            contract.setMontantContrat(contractDetails.getMontantContrat());

            return contractRepository.save(contract);
        } else {
            return null;
        }
    }

    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Optional<Contract> getContractById(Integer id) {
        return contractRepository.findById(id);
    }


}
