package com.oracleOne.Literatura.CLI;

import com.oracleOne.Literatura.model.ApiBook;
import com.oracleOne.Literatura.model.ApiResponse;
import com.oracleOne.Literatura.model.Author;
import com.oracleOne.Literatura.model.Book;
import com.oracleOne.Literatura.repository.IAuthorRepository;
import com.oracleOne.Literatura.repository.IBookRepository;
import com.oracleOne.Literatura.service.ClassMapperService;
import com.oracleOne.Literatura.service.GutendexApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CLI {

    private Scanner scanner = new Scanner(System.in);
    private GutendexApi gutenDexApi = new GutendexApi();

    private ClassMapperService classMapperService = new ClassMapperService();

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;

    public CLI(IBookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void mainMenu(){


        int option = -1;
        String menu =  """
                   \n-----------------------------------
                    Escribir opcion que desee realizar
                    -----------------------------------
                    1 - Buscar Libro por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idiomas
                    0 - Salir
                    """;

        while (option != 0){

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    searchBookByTitle();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        }


    }

    private void searchBookByTitle(){
        System.out.print("Ingresa el titulo del libro a buscar: ");
        String title = scanner.nextLine();

        String json = gutenDexApi.getBookByTitle(title.replace(" ", "+"));

        ApiResponse response = classMapperService.map(json, ApiResponse.class);

        Optional<ApiBook> result = response.getBookList().stream().findFirst();

        if(result.isPresent()){

            ApiBook apiBook = result.get();

            if(bookRepository.existsByTitle(apiBook.getTitle())){
                System.out.println("------------------------------------------------------------------");
                System.out.println("Libro ya registrado");
                System.out.println("------------------------------------------------------------------");
                return;
            }

            try{

                Book book = new Book();
                book.setTitle(apiBook.getTitle());
                book.setDownloadCount(apiBook.getDownloadCount());
                book.setLanguage(String.join(", ", apiBook.getLanguages()));

                List<Author> authors = new ArrayList<>();

                apiBook.getAuthorList().forEach(apiAuthor -> {
                    if(!authorRepository.existsByName(apiAuthor.getName())){
                        Author author = new Author(apiAuthor);
                        authorRepository.save(author);
                        authors.add(author);
                    }
                    else{
                        authors.add(authorRepository.findByName(apiAuthor.getName()).get());
                    }
                });

                book.setAuthors(authors);


                System.out.println("\n"+result.get());
                bookRepository.save(book);
                System.out.println("------------------------------------------------------------------");
                System.out.println("Libro guardado con exito");
                System.out.println("------------------------------------------------------------------");

            }catch (Exception e){
                System.out.println("------------------------------------------------------------------");
                System.out.println("Error al guardar libro");
                System.out.println("------------------------------------------------------------------");
            }
        }else{
            System.out.println("------------------------------------------------------------------");
            System.out.println("No existe Libro");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
