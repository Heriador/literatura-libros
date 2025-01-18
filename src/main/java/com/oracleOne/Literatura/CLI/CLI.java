package com.oracleOne.Literatura.CLI;

import com.oracleOne.Literatura.model.ApiBook;
import com.oracleOne.Literatura.model.ApiResponse;
import com.oracleOne.Literatura.service.ClassMapperService;
import com.oracleOne.Literatura.service.GutendexApi;

import java.util.Optional;
import java.util.Scanner;

public class CLI {

    private Scanner scanner = new Scanner(System.in);
    private GutendexApi gutenDexApi = new GutendexApi();

    private ClassMapperService classMapperService = new ClassMapperService();


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
                    6 - Reporte Estadisticos
                    7 - Top 10 libros mas Descargados
                    8 - Buscar Libros por Autor
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

        String json = gutenDexApi.getBookByTitle(title);

        ApiResponse response = classMapperService.map(json, ApiResponse.class);

        Optional<ApiBook> result = response.getBookList().stream().findFirst();

        if(result.isPresent()){
            System.out.println("\n"+result.get());
        }else{
            System.out.println("------------------------------------------------------------------");
            System.out.println("No existe Libro");
            System.out.println("------------------------------------------------------------------");
        }
    }

}
