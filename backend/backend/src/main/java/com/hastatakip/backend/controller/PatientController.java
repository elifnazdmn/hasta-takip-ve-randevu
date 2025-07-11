package com.hastatakip.backend.controller;

import com.hastatakip.backend.model.Patient;
import com.hastatakip.backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Optional;

@RestController //web API sunacağını belirtir
@RequestMapping("/api/patients") //URL adresi kökü
@CrossOrigin(origins = "*")

public class PatientController {

    @Autowired //Spring, repository’i otomatik bağlar
    private PatientRepository patientRepository;

    // Örnek: /api/patients/12345678901
    @GetMapping("/{tcKimlikNo}") //Belirli bir TC ile hasta arar
    public Optional<Patient> getPatientByTc(@PathVariable String tcKimlikNo) {
        return patientRepository.findByTcKimlikNo(tcKimlikNo);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String tc = loginData.get("tc");
        String sifre = loginData.get("password");

        Optional<Patient> patientOpt = patientRepository.findByTcKimlikNo(tc);

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            if (patient.getSifre().equals(sifre)) {
                return ResponseEntity.ok(patient); // Giriş başarılı
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Şifre yanlış");
            }
        } else {
            // TC bulunamadı → yeni hasta olarak ekle
            Patient newPatient = new Patient();
            newPatient.setTcKimlikNo(tc);
            newPatient.setSifre(sifre);
            // Diğer alanlar boş kalabilir
            patientRepository.save(newPatient);
            return ResponseEntity.ok(newPatient); // İlk girişte otomatik kayıt
        }
    }

}
