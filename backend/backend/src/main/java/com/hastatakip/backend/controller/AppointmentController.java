
package com.hastatakip.backend.controller;

import com.hastatakip.backend.model.Appointment;
import com.hastatakip.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Belirli hastanın randevularını getir
    @GetMapping("/{hastaId}")
    public List<Appointment> getAppointmentsByHastaId(@PathVariable Long hastaId) {
        return appointmentRepository.findByHastaId(hastaId);
    }

    // Randevu oluştur
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) {
        // Aynı hasta aynı saatte randevu alamaz kontrolü
        /*if (appointmentRepository.existsByHastaIdAndRandevuTarihi(appointment.getHastaId(), appointment.getRandevuTarihi())) {
            return ResponseEntity.badRequest().body("Bu saat için zaten randevunuz var.");
        }*/
        appointment.setId(System.currentTimeMillis()); //SQLite @GeneratedValue ile gelen DEFAULT SQL'ini desteklemiyor. Bu yüzden id'yi elle veriyoruz

        LocalTime appointmentTime = LocalTime.parse(appointment.getRandevuTarihi().substring(11)); // "T10:30" gibi parça alır
        if (appointmentTime.isBefore(LocalTime.of(8, 0)) || appointmentTime.isAfter(LocalTime.of(17, 0))) {
            return ResponseEntity.badRequest().body("Randevu saati 08:00 ile 17:00 arasında olmalıdır.");
        }
        Appointment saved = appointmentRepository.save(appointment);
        return ResponseEntity.ok(saved);
    }

    // Randevu sil
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return ResponseEntity.ok("Randevu silindi.");
    }
}
