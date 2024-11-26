package com.example.RentalMobil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.RentalMobil.Entity.Mobil;
import com.example.RentalMobil.Service.MobilService;

@Controller
@RequestMapping("/mobils")
public class MobilController {

    @Autowired
    MobilService mobilService;

    @GetMapping
    public String listMobils(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("authenticatedUser", authentication.getPrincipal());
        }
        model.addAttribute("mobils", mobilService.findAll());
        return "mobil/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createMobilForm(Model model) {
        model.addAttribute("mobil", new Mobil());
        return "mobil/create"; 
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createMobil(
        @ModelAttribute Mobil mobil,
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        try {
            mobilService.save(mobil, file);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "mobil/create";
        }
        return "redirect:/mobils";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteMobil(@PathVariable long id) {
        try {
            mobilService.delete(id);
        } catch (Exception e) {
            return "redirect:/mobils?error=" + e.getMessage();
        }
        return "redirect:/mobils";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{id}")
    public String editMobilForm(@PathVariable long id, Model model) {
        model.addAttribute("mobil", mobilService.findById(id));
        return "mobil/edit";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/{id}")
    public String editMobil(
        @PathVariable long id,
        @ModelAttribute Mobil mobil,
        @RequestParam("file") MultipartFile file,
        Model model
    ) {
        try {
            mobilService.update(mobil, file);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "mobil/edit"; 
        }
        return "redirect:/mobils";
    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable long id) {
        Mobil mobil = mobilService.findById(id);
        return mobil.getImage();
    }
}
