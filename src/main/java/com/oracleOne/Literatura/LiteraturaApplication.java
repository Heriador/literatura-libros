package com.oracleOne.Literatura;

import com.oracleOne.Literatura.service.GutendexApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		GutendexApi gutenDexApi = new GutendexApi();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello World from CommandLineRunner");

		System.out.print("Enter the title of the book you want to search: ");
		String title = scanner.nextLine();

		System.out.println(gutenDexApi.getBookByTitle(title));
	}
}
