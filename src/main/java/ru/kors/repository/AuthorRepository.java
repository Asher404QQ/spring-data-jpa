package ru.kors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kors.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
