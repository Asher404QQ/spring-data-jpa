package ru.kors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "section_id")
    private Long id;
    @Column(name = "section_name", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String name;
    @Column(name = "section_description", columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
    @OneToMany(mappedBy = "section")
    private Set<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(id, section.id) && Objects.equals(name, section.name) && Objects.equals(description, section.description) && Objects.equals(blog, section.blog) && Objects.equals(posts, section.posts);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
