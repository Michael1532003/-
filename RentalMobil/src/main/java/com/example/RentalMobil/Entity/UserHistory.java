package com.example.RentalMobil.Entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
@Entity
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String aksi;
    private String keterangan;
    private Timestamp timestamp;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAksi() {
        return aksi;
    }
    public void setAksi(String aksi) {
        this.aksi = aksi;
    }
    public String getKeterangan() {
        return keterangan;
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
}
