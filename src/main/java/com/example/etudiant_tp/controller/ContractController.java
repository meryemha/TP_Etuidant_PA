package com.example.etudiant_tp.controller;

import com.example.etudiant_tp.model.Contract;
import com.example.etudiant_tp.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @PostMapping("/add")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        return ResponseEntity.ok(contractService.addContract(contract));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Integer id, @RequestBody Contract contractDetails) {
        return ResponseEntity.ok(contractService.updateContract(id, contractDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer id) {
        contractService.deleteContract(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Contract>> getContractById(@PathVariable Integer id) {
        return ResponseEntity.ok(contractService.getContractById(id));
    }




}
