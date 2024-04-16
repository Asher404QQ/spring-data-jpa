package ru.kors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kors.model.Author;
import ru.kors.repository.AuthorRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository repository;

    @PostMapping("/save")
    public Author saveAuthor(@RequestBody Author author) {
        return repository.save(author);
    }

    @GetMapping("/find-by-id/{id}")
    public Optional<Author> findAuthorById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/update")
    public Author updateAuthor(@RequestBody Author author) {
        return repository.save(author);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
