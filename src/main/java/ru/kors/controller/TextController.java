package ru.kors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kors.model.Text;
import ru.kors.repository.TextRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resource/text")
@RequiredArgsConstructor
public class TextController {
    private final TextRepository repository;

    @PostMapping("/save-text")
    public Text saveText(@RequestBody Text text) {
        repository.save(text);
        return text;
    }

    @GetMapping("/find-by-id/{id}")
    public Optional<Text> findById(@PathVariable Long id) {
        return repository.findById(id);
    }
}

