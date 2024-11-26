package com.example.RentalMobil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.RentalMobil.Entity.Mobil;
import com.example.RentalMobil.Entity.UserHistory;
import com.example.RentalMobil.Repository.MobilRepository;

import java.io.IOException;
import java.util.List;

@Service
public class MobilService {
    @Autowired
    private MobilRepository mobilRepository;
    @Autowired
    private UserHistoryService userHistoryService;

    public List<Mobil> findAll() {
        return mobilRepository.findAll();
    }

    public void save(Mobil mobil, MultipartFile file) throws IllegalArgumentException, IOException, MaxUploadSizeExceededException {
        if (mobil.getHarga() < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }

        if (!file.isEmpty()) {
            try {
                mobil.setImage(file.getBytes());
            } catch (IOException e) {
                throw new IOException("Failed to upload image");
            }
        }

        UserHistory userHistory = new UserHistory();
        userHistory.setAksi("CREATE");
        userHistory.setKeterangan("Mobil " + mobil.getMerk() + " ditambahkan");
        userHistory.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        userHistoryService.saveUserHistory(userHistory);

        mobilRepository.save(mobil);
    }

    public Mobil findById(long id) {
        UserHistory userHistory = new UserHistory();
        userHistory.setAksi("GET");
        userHistory.setKeterangan("Mobil " + mobilRepository.findById(id).get().getMerk() + " diambil");
        userHistory.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        userHistoryService.saveUserHistory(userHistory);
        return mobilRepository.findById(id).orElse(null);
    }

    public Mobil update(Mobil mobil, MultipartFile file) throws IllegalArgumentException, IOException, MaxUploadSizeExceededException {
        if (mobil.getHarga() < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }

        long id = mobil.getId();
        Mobil newMobil = findById(id);
        if (newMobil != null) {
            newMobil.setMerk(mobil.getMerk());
            newMobil.setTipe(mobil.getTipe());
            newMobil.setWarna(mobil.getWarna());
            newMobil.setHarga(mobil.getHarga());

            if (!file.isEmpty()) {
                try {
                    newMobil.setImage(file.getBytes());
                } catch (IOException e) {
                    throw new IOException("Failed to upload image");
                }
            }

            UserHistory userHistory = new UserHistory();
            userHistory.setAksi("UPDATE");
            userHistory.setKeterangan("Mobil " + mobilRepository.findById(id).get().getMerk() + " diupdate");
            userHistory.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
            userHistoryService.saveUserHistory(userHistory);

            return mobilRepository.save(newMobil);
        } else {
            throw new IllegalArgumentException("Mobil not found");
        }
    }

    public void delete(long id) throws IllegalArgumentException {
        Mobil mobil = findById(id);
        if (mobil != null) {
            UserHistory userHistory = new UserHistory();
            userHistory.setAksi("DELETE");
            userHistory.setKeterangan("Mobil " + mobil.getMerk() + " dihapus");
            userHistory.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
            userHistoryService.saveUserHistory(userHistory);

            mobilRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Mobil not found");
        }
    }
}
