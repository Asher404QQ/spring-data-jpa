package ru.kors.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.List;

@Check(constraints = "rating >= 0 and rating <= 10")
@Check(constraints = "number_of_views >= 0")
@Data
@Entity
@Table(
        name = "author",
        schema = "my_schema",
        indexes = {
                @Index(columnList = "firstName, lastName"),
                @Index(columnList = "alias")
        }
)
@NoArgsConstructor
public class Author extends BaseEntityStats{
    @Id
    @SequenceGenerator(name = "author_seq1",
        sequenceName = "author_sequence",
        initialValue = 1, allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq1")
    @Column(name = "author_id")
    private Long id;
    @Column(name = "alias", columnDefinition = "TEXT")
    private String alias;
    @Column(name = "first_name", columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email", columnDefinition = "TEXT UNIQUE NOT NULL")
    private String email;
    @ManyToMany
    @JoinTable(name = "author_blog",
        schema = "my_schema",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = {
                @JoinColumn(name = "blog_id")
            }
    )
    private List<Blog> blogs;
    @OneToMany
    @JoinColumn(name = "role_id")
    private List<Role> roles;
}
