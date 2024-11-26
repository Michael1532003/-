package com.example.RentalMobil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RentalMobil.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
