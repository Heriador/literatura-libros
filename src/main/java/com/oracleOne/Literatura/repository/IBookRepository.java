package com.oracleOne.Literatura.repository;

import com.oracleOne.Literatura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);

}
