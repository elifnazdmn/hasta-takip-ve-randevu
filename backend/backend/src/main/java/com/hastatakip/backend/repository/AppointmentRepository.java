package com.hastatakip.backend.repository;

import com.hastatakip.backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByHastaId(Long hastaId);
    boolean existsByHastaIdAndRandevuTarihi(Long hastaId, String randevuTarihi);
}
