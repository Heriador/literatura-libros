# literatura-libros

# API de Gestión de Libros - Challenge Alura Latam  

Este proyecto es una aplicación basada en consola que permite interactuar con datos de libros proporcionados por la API [Gutendex](https://gutendex.com/) y almacenarlos en una base de datos local. Forma parte de un challenge de Alura Latam.  

## Funcionalidades  

1. **Buscar un libro:**  
   - Permite buscar libros en la API de Gutendex.  
   - Si el libro ya existe en la base de datos, no se vuelve a agregar.  

2. **Mostrar todos los libros almacenados:**  
   - Lista todos los libros que han sido buscados y almacenados en la base de datos.  

3. **Mostrar todos los autores almacenados:**  
   - Lista todos los autores que de libros que han sido buscado y almacenados en la base de datos.  

4. **Autores vivos en un año específico:**  
   - Lista los autores vivos en un año determinado, basándose en los datos de los libros guardados.  

5. **Lista libros por idioma:**  
   - Muestra una lista de todos los libros escritos en un determinado idioma.
     
0. **Salir:**  
   - Cierra la aplicación.  

## Tecnologías Utilizadas  

- **Java Spring Boot:** Desarrollo del backend.  
- **Spring Data JPA:** Gestión de la persistencia y consultas a la base de datos.  
- **PostgreSQL:** Base de datos utilizada para almacenar los libros y autores.  
- **Jackson:** Procesamiento de JSON para consumir la API de Gutendex.  
- **Driver de PostgreSQL:** Conexión entre la aplicación y la base de datos.  

## Requisitos Previos  

- JDK 17 o superior.  
- PostgreSQL instalado y configurado.  
- Maven para la gestión de dependencias.  

