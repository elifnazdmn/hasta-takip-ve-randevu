package com.hastatakip.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patients") //sql deki tabloyla eşleştiriyoruz.
@Data //Getter, Setter, toString, equals gibi kodları otomatik üretir (Lombok)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tc_kimlik_no", nullable = false, unique = true) // java alanlarını sql sütunlarıyla eşler
    private String tcKimlikNo;

    @Column(name = "ad_soyad")
    private String adSoyad;

    @Column(name = "dogum_tarihi")
    private String dogumTarihi;

    @Column(name = "telefon")
    private String telefon;
}
