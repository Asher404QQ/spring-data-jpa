package ru.kors.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("FILE")
public class File extends Resource{
    @Column(name = "file", columnDefinition = "OID UNIQUE")
    @Lob
    private byte[] file;
}

