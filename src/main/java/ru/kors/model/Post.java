package ru.kors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "post_name", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String name;
    @ManyToOne
    private Section section;
    @OneToOne
    @JoinColumn(name = "resource_name")
    @JoinColumn(name = "resource_url")
    private Resource resource;
    @Embedded
    private BaseEntityMetadata baseEntityMetadata;
}
