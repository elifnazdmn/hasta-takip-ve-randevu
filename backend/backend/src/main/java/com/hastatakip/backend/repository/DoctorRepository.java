package com.hastatakip.backend.repository;

import com.hastatakip.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDepartman(String departman);
}
