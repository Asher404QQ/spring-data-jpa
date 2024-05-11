package ru.kors.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kors.model.Author;
import ru.kors.model.AuthorProjection;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("from Author au join fetch au.role")
    List<Author> findAllJoinFetch();
    @EntityGraph(attributePaths = "role")
    List<Author> findByOrderById();
    List<AuthorProjection> findFirst5ByOrderByAlias();
    List<Author> findByLessThanEqualIdProop(Long id);
    List<Author> findByAliasOrLastNameProp(@Param("pattern") String pattern);
    @Query("from Author au where lower(au.firstName) = lower(?1) and upper(au.lastName) = upper(?2)")
    List<Author> findByFirstNameAndLastName(String firstName, String lastName);
    @Query("""
    select au from Author au
    where lower(au.alias) like lower(concat('%', :infix, '%')) or
     lower(au.email) like lower(concat('%', :infix, '%'))
    """)
    List<Author> findByAliasOrEmail(@Param("infix") String infix);

    @Query("from Author au where au.firstName = :firstName and au.lastName = :lastName or au.email = :email")
    List<Author> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);
    @Query(nativeQuery = true, value = "select * from data_jpa.my_schema.author")
    List<Author> findAllSQLQuery();

    @Modifying
    @Transactional
    @Query("update Author au set au.alias = :alias where au.id = :id")
    int updateAliasById(Long id, String alias);
    // select * from author where alias = ?
    List<Author> findByAliasIgnoreCase(String alias);
    // select * from author where first_name=? and last_name=?
    List<Author> findByFirstNameOrLastName(String firstName, String lastName);
    List<Author> findByFirstNameOrLastNameOrderByEmailAsc(String firstName, String lastName);
    // select * from author where email like 'prefix%'
    List<Author> readByEmailIgnoreCaseStartingWith(String prefix);
    List<Author> readByEmailStartingWith(String prefix, Sort sort);
    List<Author> readByEmailStartingWith(String prefix, Pageable pageable);
    //select * from author where email like '%suffix'
    List<Author> getByEmailIgnoreCaseEndingWith(String suffix);
    //select * from author where email like '%infix%'
    List<Author> queryByAliasIgnoreCaseContaining(String infix);
    // select * from author where rating < ?
    List<Author> findByRatingLessThanOrderByRatingDesc(Double rating);
    // select * from author where rating >= ?
    List<Author> findByRatingGreaterThanEqualOrderByRatingAsc(Double rating);
    // select * from author where rating between ? and ?
    List<Author> findByRatingBetweenOrderByRatingAsc(Double start, Double end);
    // select distinct(*) from author where upper(first_name) like upper(?)
    List<Author> findDistinctByFirstNameIgnoreCaseStartingWith(String firstName);
    // select count(author_id) from author where number_of_views <= ?
    Long countByNumberOfViewsLessThanEqual(Long numberOfViews);
}
