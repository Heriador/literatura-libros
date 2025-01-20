package com.oracleOne.Literatura;


import com.oracleOne.Literatura.CLI.CLI;
import com.oracleOne.Literatura.service.AuthorService;
import com.oracleOne.Literatura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

    @Override
	public void run(String... args) throws Exception {

		CLI cli = new CLI(bookService, authorService);
		cli.mainMenu();

	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}


}
