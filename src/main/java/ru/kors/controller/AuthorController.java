package ru.kors.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import ru.kors.model.Author;
import ru.kors.model.AuthorProjection;
import ru.kors.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository repository;
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/find-all-join-fetch")
    public List<Author> findAllJoinFetch() {
        return repository.findAllJoinFetch();
    }

    @GetMapping("/find-all-entity-graph")
    public List<Author> findByOrderById() {
        return repository.findByOrderById();
    }

    @GetMapping("/find-all-lazy-init")
    public List<AuthorProjection> findAllFetchTypeLazy() {
        return repository.findFirst5ByOrderByAlias();
    }

    @GetMapping("/find-by-email-domain/{emailDomain}")
    public List<Author> findByEmailDomain(@PathVariable String emailDomain) {
        Query query = manager.createNamedQuery("Author.findByEmailDomain");
        query.setParameter(1, emailDomain);
        return query.getResultList();
    }
    @GetMapping("/find-all-by-native-query")
    public List<Author> findAllByNativeQuery() {
        Query query = manager.createNamedQuery("Author.findAllByNativeQuery");
        return query.getResultList();
    }

    @GetMapping("/find-by-id-prop/{id}")
    public List<Author> findByLessThanEqualIdProop(@PathVariable Long id) {
        return repository.findByLessThanEqualIdProop(id);
    }

    @GetMapping("/find-by-alias-or-lastName-prop/{word}")
    public List<Author> findByAliasOrLastNameProp(@PathVariable String word) {
        String pattern = "%" + word + "%";
        return repository.findByAliasOrLastNameProp(pattern);
    }

    @GetMapping("/find-by-firstName-and-lastName/{firstName}/{lastName}")
    public List<Author> findByFirstNameAndLastName(@PathVariable String firstName,
                                                   @PathVariable String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/find-by-alias-or-email/{infix}")
    public List<Author> findByAliasOrEmail(@PathVariable String infix) {
        return repository.findByAliasOrEmail(infix);
    }

    @GetMapping("/find-by-full-name-or-email/{firstName}/{lastName}/{email}")
    public List<Author> findByFirstNameAndLastNameOrEmail(@PathVariable String firstName,
                                                          @PathVariable String lastName,
                                                          @PathVariable String email) {
        return repository.findByFirstNameAndLastNameOrEmail(firstName, lastName, email);
    }

    @GetMapping("/find-all-sql-query")
    public List<Author> findAllSQL() {
        return repository.findAllSQLQuery();
    }

    @PutMapping("/update-alias-by-id/{id}")
    public int updateAliasById(@PathVariable Long id,
                               @RequestBody String alias){
        return repository.updateAliasById(id, alias);
    }


    @GetMapping("/find-by-alias/{alias}")
    public List<Author> findByAliasIgnoreCase(@PathVariable String alias) {
        return repository.findByAliasIgnoreCase(alias);
    }

    @GetMapping("/find-by-firstName-or-lastName/{firstName}/{lastName}")
    public List<Author> findByFirstNameOrLastName(@PathVariable String firstName,
                                                  @PathVariable String lastName) {
        return repository.findByFirstNameOrLastName(firstName, lastName);
    }

    @GetMapping("/read-by-email/{prefix}")
    public List<Author> readByEmailIgnoreCaseStartingWith(@PathVariable String prefix) {
        return repository.readByEmailIgnoreCaseStartingWith(prefix);
    }

    @GetMapping("/get-by-email/{suffix}")
    public List<Author> getByEmailIgnoreCaseEndingWith(@PathVariable String suffix) {
        return repository.getByEmailIgnoreCaseEndingWith(suffix);
    }

    @GetMapping("/query-by-alias/{infix}")
    public List<Author> queryByAliasIgnoreCaseContaining(@PathVariable String infix) {
        return repository.queryByAliasIgnoreCaseContaining(infix);
    }

    @GetMapping("/find-by-rating-less-than/{rating}")
    public List<Author> findByRatingLessThanOrderByRatingDesc(@PathVariable Double rating) {
        return repository.findByRatingLessThanOrderByRatingDesc(rating);
    }

    @GetMapping("/find-by-rating-greater-than-equal/{rating}")
    public List<Author> findByRatingGreaterThanEqualOrderByRatingAsc(@PathVariable Double rating) {
        return repository.findByRatingGreaterThanEqualOrderByRatingAsc(rating);
    }

    @GetMapping("/find-by-rating-between/{start}/{end}")
    public List<Author> findByRatingBetweenOrderByRatingAsc(@PathVariable Double start,
                                                            @PathVariable Double end) {
        return repository.findByRatingBetweenOrderByRatingAsc(start, end);
    }

    @GetMapping("/find-distinct-by-first-name-starting-with/{firstName}")
    public List<Author> findDistinctByFirstName(@PathVariable String firstName) {
        return repository.findDistinctByFirstNameIgnoreCaseStartingWith(firstName);
    }

    @GetMapping("/count-by-number-of-views-less-than-equal/{numberOfViews}")
    public Long countByNumberOfViewsLessThanEqual(@PathVariable Long numberOfViews) {
        return repository.countByNumberOfViewsLessThanEqual(numberOfViews);
    }


    @GetMapping("/find-authors")
    public List<Author> findAuthors(@RequestBody Author author) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("alias", match -> match.startsWith().ignoreCase());
        return repository.findAll(Example.of(author, matcher));
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<Author> pageAuthor(@PathVariable int pageNumber,
                                   @PathVariable int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Author> page = repository.findAll(pageable);
        return page.getContent();
    }

    @GetMapping("/sort/{fieldName}")
    public List<Author> sort(@PathVariable String fieldName) {
        Sort sort = Sort.by(fieldName).ascending();
        return repository.findAll(sort);
    }

    @GetMapping("/find-all")
    public List<Author> findAll() {
        return repository.findAll();
    }

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
