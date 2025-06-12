package com.workintech.sqldmljoins.entity;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

@NamedNativeQuery(name="findClassesWithBookCount", query = "SELECT o.sinif, count(i.kitapno) " +
        " FROM ogrenci as o " +
        " INNER JOIN islem as i " +
        " ON o.ogrno = i.ogrno " +
        " WHERE (o.sinif = '10A' OR o.sinif='10B') " +
        " GROUP BY o.sinif ", resultSetMapping = "Mapping.KitapCount")
@SqlResultSetMapping(name="Mapping.KitapCount", classes = @ConstructorResult(targetClass = KitapCount.class,
        columns = {@ColumnResult(name = "sinif"),
                @ColumnResult(name = "count")}))
public interface StudentNameCount {
    String getAd();
    Integer getCount();
}
