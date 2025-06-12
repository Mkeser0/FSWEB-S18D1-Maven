package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    // Kitap alan öğrencileri getir
    String QUESTION_2 = "SELECT * FROM ogrenci WHERE ogrno IN (SELECT ogrno FROM islem)";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();

    // Kitap almayan öğrencileri getir
    String QUESTION_3 = "SELECT * FROM ogrenci o WHERE o.ogrno NOT IN (SELECT DISTINCT i.ogrno FROM islem i)";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    // 10A veya 10B sınıfındaki öğrencilerin sınıf ve kitap sayısı
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.kitapno) AS kitap_sayisi " +
            "FROM ogrenci o " +
            "LEFT JOIN islem i ON o.ogrno = i.ogrno " +
            "WHERE o.sinif IN ('10A', '10B') " +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    // Toplam öğrenci sayısı
    String QUESTION_5 = "SELECT COUNT(*) FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    // Farklı isimdeki öğrenci sayısı
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad) FROM ogrenci";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    // İsme göre öğrenci sayısı
    String QUESTION_7 = "SELECT ad, COUNT(*) AS adet FROM ogrenci GROUP BY ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    // Her sınıftaki öğrenci sayısı
    String QUESTION_8 = "SELECT sinif, COUNT(*) AS ogrenci_sayisi FROM ogrenci GROUP BY sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    // Öğrencilerin ad soyad + okuduğu kitap sayısı
    String QUESTION_9 = "SELECT o.ad, o.soyad, COUNT(i.kitapno) AS kitap_sayisi " +
            "FROM ogrenci o " +
            "LEFT JOIN islem i ON o.ogrno = i.ogrno " +
            "GROUP BY o.ad, o.soyad";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
