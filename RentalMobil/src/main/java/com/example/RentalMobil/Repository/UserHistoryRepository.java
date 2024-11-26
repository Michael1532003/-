package com.example.RentalMobil.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RentalMobil.Entity.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findAllByOrderByTimestampDesc();
}
