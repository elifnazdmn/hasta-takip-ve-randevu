package com.hastatakip.backend.controller;

import com.hastatakip.backend.model.Patient;
import com.hastatakip.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController //web API sunacağını belirtir
@RequestMapping("/api/patients") //URL adresi kökü
public class PatientController {

    @Autowired //Spring, repository’i otomatik bağlar
    private PatientRepository patientRepository;

    // Örnek: /api/patients/12345678901
    @GetMapping("/{tcKimlikNo}") //Belirli bir TC ile hasta arar
    public Optional<Patient> getPatientByTc(@PathVariable String tcKimlikNo) {
        return patientRepository.findByTcKimlikNo(tcKimlikNo);
    }
}
