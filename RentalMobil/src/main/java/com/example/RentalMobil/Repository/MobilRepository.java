package com.example.RentalMobil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RentalMobil.Entity.Mobil;

public interface MobilRepository extends JpaRepository<Mobil, Long> {
    
}
