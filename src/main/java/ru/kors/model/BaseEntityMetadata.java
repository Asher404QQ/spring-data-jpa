package ru.kors.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BaseEntityMetadata {
    private LocalDate createdDate;
    private String createBy;
    private LocalDate modifiedDate;
    private String modifiedBy;
}
