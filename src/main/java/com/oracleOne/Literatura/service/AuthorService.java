package com.oracleOne.Literatura.service;

import com.oracleOne.Literatura.model.Author;
import com.oracleOne.Literatura.repository.IAuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    public boolean existsByName(String name) {
        return authorRepository.existsByName(name);
    }

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThan(Integer year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThan(year,year);
    }
}
