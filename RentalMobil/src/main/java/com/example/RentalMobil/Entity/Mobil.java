package com.example.RentalMobil.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Mobil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String merk;
    private String tipe;
    private String warna;
    private double harga;

    @Lob
    private byte[] image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Mobil() {
    }

    public Mobil(long id, String merk, String tipe, String warna, long harga, byte[] image) {
        this.id = id;
        this.merk = merk;
        this.tipe = tipe;
        this.warna = warna;
        this.harga = harga;
        this.image = image;
    }

    
}
