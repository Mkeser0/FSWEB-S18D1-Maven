package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitapRepository extends JpaRepository<Kitap, Long> {

    // Dram ve Hikaye türündeki kitapları getir (JOIN'siz)
    String QUESTION_1 = "SELECT * FROM kitap WHERE turno IN (SELECT turno FROM tur WHERE ad IN ('Dram', 'Hikaye'))";
    @Query(value = QUESTION_1, nativeQuery = true)
    List<Kitap> findBooks();

    // Kitapların ortalama puanı
    String QUESTION_10 = "SELECT AVG(puan) FROM kitap";
    @Query(value = QUESTION_10, nativeQuery = true)
    Double findAvgPointOfBooks();
}
