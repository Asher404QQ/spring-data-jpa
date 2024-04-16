package ru.kors.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Embeddable
public class BaseEntityStats {
    @Column(name = "number_of_views")
    private Long numberOfViews;
    @Column(name = "rating")
    private Double rating;
}
