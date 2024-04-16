package ru.kors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "resource_id")
    private Long id;
    @Column(name = "resource_name", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String name;
    @Column(name = "resource_url", columnDefinition = "TEXT NOT NULL")
    private String url;
    @Column(name = "resuorce_size")
    private Integer size;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
