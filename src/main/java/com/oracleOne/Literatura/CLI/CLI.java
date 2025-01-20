package com.oracleOne.Literatura.CLI;

import com.oracleOne.Literatura.model.ApiBook;
import com.oracleOne.Literatura.model.ApiResponse;
import com.oracleOne.Literatura.model.Author;
import com.oracleOne.Literatura.model.Book;
import com.oracleOne.Literatura.service.AuthorService;
import com.oracleOne.Literatura.service.BookService;
import com.oracleOne.Literatura.service.ClassMapperService;
import com.oracleOne.Literatura.service.GutendexApi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CLI {

    private Scanner scanner = new Scanner(System.in);
    private GutendexApi gutenDexApi = new GutendexApi();

    private ClassMapperService classMapperService = new ClassMapperService();

    private BookService bookService;
    private AuthorService authorService;


    public CLI(BookService bookService, AuthorService authorService) {
      this.bookService = bookService;
      this.authorService = authorService;
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
            System.out.println("Opcion: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    System.out.println("-----------------------------LISTA DE LIBROS-------------------------------------");
                    bookService.findAllBooks().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("-----------------------------LISTA DE AUTORES-------------------------------------");
                    authorService.findAllAuthors().forEach(System.out::println);
                    break;
                case 4:
                    searchAliveAuthorsInGivenYear();
                    break;
                case 5:
                    searchBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Saliendo...");
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

            if(bookService.existsByTitle(apiBook.getTitle())){
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
                    if(!authorService.existsByName(apiAuthor.getName())){
                        Author author = new Author(apiAuthor);
                        authorService.saveAuthor(author);
                        authors.add(author);
                    }
                    else{
                        authors.add(authorService.findByName(apiAuthor.getName()).get());
                    }
                });

                book.setAuthors(authors);


                System.out.println("\n"+result.get());
                bookService.saveBook(book);
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

    private void searchAliveAuthorsInGivenYear(){
        System.out.print("Ingresa el año: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("------------------------------------------------------------------");
        List<Author> authors = authorService.findByBirthYearLessThanEqualAndDeathYearGreaterThan(year);

        if(authors.isEmpty()){
            System.out.println("No hay autores vivos en ese año");
        }
        else{
            authors.forEach(System.out::println);
        }
        System.out.println("------------------------------------------------------------------");
    }

    private void searchBooksByLanguage(){
        System.out.print("Ingresa el idioma: ");
        String language = scanner.nextLine();
        System.out.println("------------------------------------------------------------------");
        List<Book> books = bookService.findByLanguage(language);

        if(books.isEmpty()){
            System.out.println("No hay libros en ese idioma");
        }
        else{
            books.forEach(System.out::println);
        }
        System.out.println("------------------------------------------------------------------");
    }
}
