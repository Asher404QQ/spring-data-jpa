package ru.kors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.kors.model.Text;
import ru.kors.repository.TextRepository;
import ru.kors.specs.SearchCriteria;
import ru.kors.specs.SearchCriteria.SearchOperation;
import ru.kors.specs.TextSpecification;

import java.util.List;
import java.util.Optional;

import static ru.kors.specs.SearchCriteria.SearchOperation.*;

@RestController
@RequestMapping("/api/v1/resource/text")
@RequiredArgsConstructor
public class TextController {
    private final TextRepository repository;

    @GetMapping("/find-all")
    public List<Text> findAll() {
        return repository.findAll();
    }

    @GetMapping("/find-by-name/{name}")
    public List<Text> findByName(@PathVariable String name) {
        TextSpecification specification = new TextSpecification();
        specification.add(new SearchCriteria("name", name, EQUAL));
        return repository.findAll(specification);
    }

    @GetMapping("/find-by-size-gte/{size}")
    public List<Text> findBySizeGreaterThanEqual(@PathVariable Integer size) {
        TextSpecification specification = new TextSpecification();
        specification.add(new SearchCriteria("size", size, GREATER_THAN_EQUAL));
        return repository.findAll(specification, Sort.by("size").ascending());
    }

    @GetMapping("/find-by-path-and-text")
    public List<Text> findByPathAndText(@RequestBody Text text) {
        TextSpecification urlSpec = new TextSpecification();
        urlSpec.add(new SearchCriteria("url", text.getUrl(), STARTING_WITH));
        TextSpecification textSpec = new TextSpecification();
        textSpec.add(new SearchCriteria("text", text.getText(), ENDING_WITH));
        return repository.findAll(Specification.where(urlSpec).and(textSpec));
    }

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

