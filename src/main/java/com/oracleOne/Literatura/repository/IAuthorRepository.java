package com.oracleOne.Literatura.repository;

import com.oracleOne.Literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    boolean existsByName(String name);

    Optional<Author> findByName(String name);

    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThan(Integer birthYear, Integer deathYear);
}
