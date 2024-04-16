package ru.kors.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(
        name = "text_id",
        referencedColumnName = "resource_id"
)
@DiscriminatorValue("TEXT")
public class Text extends Resource{
    @Column(name = "text", columnDefinition = "TEXT UNIQUE")
    private String text;
}

