package com.workintech.sqldmljoins.entity;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

@NamedNativeQuery(name="findStudentNameSurnameCount", query = "SELECT o.ad, o.soyad, COUNT(i.kitapno) FROM ogrenci as o " +
        " INNER JOIN islem as i " +
        " ON o.ogrno = i.ogrno " +
        " GROUP BY o.ad, o.soyad" +
        " GROUP BY o.sinif ", resultSetMapping = "Mapping.KitapCount")
@SqlResultSetMapping(name="Mapping.KitapCount", classes = @ConstructorResult(targetClass = KitapCount.class,
        columns = {@ColumnResult(name = "sinif"),
                @ColumnResult(name = "count")}))
public interface StudentNameSurnameCount {
    String getAd();
    String getSoyad();
    Integer getCount();
}
