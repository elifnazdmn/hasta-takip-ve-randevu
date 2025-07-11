package com.hastatakip.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    @Column(name = "sifre")
    private String sifre;

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }


}
