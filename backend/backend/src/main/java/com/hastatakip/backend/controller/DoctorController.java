package com.hastatakip.backend.controller;

import com.hastatakip.backend.model.Doctor;
import com.hastatakip.backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/by-departman/{departman}")
    public List<Doctor> getDoctorsByDepartman(@PathVariable String departman) {
        return doctorRepository.findByDepartman(departman);
    }
}
