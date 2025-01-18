package com.oracleOne.Literatura;


import com.oracleOne.Literatura.CLI.CLI;
import com.oracleOne.Literatura.repository.IAuthorRepository;
import com.oracleOne.Literatura.repository.IBookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@RequiredArgsConstructor
public class LiteraturaApplication implements CommandLineRunner {

	private final IBookRepository bookRepository;

	private final IAuthorRepository authorRepository;

    public LiteraturaApplication(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
	public void run(String... args) throws Exception {

		CLI cli = new CLI(bookRepository, authorRepository);
		cli.mainMenu();

	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}


}
