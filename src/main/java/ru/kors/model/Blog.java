package ru.kors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "blog_id")
    private Long id;
    @Column(name = "blog_title", columnDefinition = "TEXT NOT NULL")
    private String title;
    @Column(name = "blog_description", columnDefinition = "TEXT")
    private String description;
//    @ManyToMany(mappedBy = "blogs")
//    private List<Author> authors;
    @OneToMany(mappedBy = "blog")
    private List<Section> sections;
    @Embedded
    @AttributeOverride(
            name = "createdBy",
            column = @Column(name = "blog_created_by")
    )
    private BaseEntityMetadata baseEntityMetadata;
    @Embedded
    private BaseEntityStats baseEntityStats;

//    public void addAuthor(Author author) {
//        this.authors.add(author);
//        author.getBlogs().add(this);
//    }
//
//    public void removeAuthor(Author author) {
//        this.authors.remove(author);
//        author.getBlogs().remove(this);
//    }

}
