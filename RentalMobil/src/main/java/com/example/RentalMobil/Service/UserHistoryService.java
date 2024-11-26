package com.example.RentalMobil.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RentalMobil.Entity.UserHistory;
import com.example.RentalMobil.Repository.UserHistoryRepository;

@Service
public class UserHistoryService {
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    
    public void saveUserHistory(UserHistory userHistory) {
        userHistoryRepository.save(userHistory);
    }

    public List<UserHistory> findAllSortedByTimestampDesc() {
        return userHistoryRepository.findAllByOrderByTimestampDesc();
    }

    public void deleteUserHistory(long id) {
        userHistoryRepository.deleteById(id);
    }
}
