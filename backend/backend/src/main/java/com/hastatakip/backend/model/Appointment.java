package com.hastatakip.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;



@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hasta_id")
    private Long hastaId;

    @Column(name = "doktor_adi")
    private String doktorAdi;

    @Column(name = "randevu_tarihi")
    private String randevuTarihi;

    @Column(name = "notlar")
    private String notlar;

    @Column(name = "departman")
    private String departman;

    // Getter Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getHastaId() { return hastaId; }
    public void setHastaId(Long hastaId) { this.hastaId = hastaId; }

    public String getDoktorAdi() { return doktorAdi; }
    public void setDoktorAdi(String doktorAdi) { this.doktorAdi = doktorAdi; }

    public String getRandevuTarihi() { return randevuTarihi; }
    public void setRandevuTarihi(String randevuTarihi) { this.randevuTarihi = randevuTarihi; }

    public String getNotlar() { return notlar; }
    public void setNotlar(String notlar) { this.notlar = notlar; }

    public String getDepartman() { return departman; }
    public void setDepartman(String departman) { this.departman = departman; }
}
