package com.example.RentalMobil.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.RentalMobil.Entity.UserHistory;
import com.example.RentalMobil.Service.UserHistoryService;

@Controller
public class UserHistoryController {
    
    @Autowired
    private UserHistoryService userHistoryService;

    @GetMapping("/UserHistory")
    public String listUserHistory(Model model) {
        List<UserHistory> userHistorys = userHistoryService.findAllSortedByTimestampDesc();
        model.addAttribute("userHistorys", userHistorys);
        return "UserHistory";
    }
}
