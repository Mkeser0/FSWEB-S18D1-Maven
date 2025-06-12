package com.workintech.sqldmljoins.entity;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;

@NamedNativeQuery(name="findStudentClassCount", query = "SELECT sinif, COUNT(sinif) FROM ogrenci GROUP BY sinif",
        resultSetMapping = "Mapping.StudentClassCount")
@SqlResultSetMapping(name="Mapping.StudentClassCount", classes = @ConstructorResult(targetClass = StudentClassCount.class,
        columns = {@ColumnResult(name = "sinif"),
                @ColumnResult(name = "count")}))
public interface StudentClassCount {
    String getSinif();
    Integer getCount();
}
