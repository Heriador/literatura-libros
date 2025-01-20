package com.oracleOne.Literatura.service;

import com.oracleOne.Literatura.model.Book;
import com.oracleOne.Literatura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookRepository bookRepository;

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

}
