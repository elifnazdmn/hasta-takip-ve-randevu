package com.hastatakip.backend.repository;

import com.hastatakip.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> { //Generic olarak hasta tablosuna erişimi sağlar
    Optional<Patient> findByTcKimlikNo(String tcKimlikNo); // Bulunamazsa null yerine boş döner
}
