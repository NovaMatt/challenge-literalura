package com.example.literalura.principal;

import com.example.literalura.model.AutorEntity;
import com.example.literalura.model.LibroEntity;
import com.example.literalura.model.Respuesta;
import com.example.literalura.repository.IAutorRepository;
import com.example.literalura.repository.ILibroRepository;
import com.example.literalura.service.ConsumoAPI;
import com.example.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    String URL_BASE="https://gutendex.com/books/?search=";

    private Scanner teclado = new Scanner(System.in);
    ConvierteDatos convierteDatos = new ConvierteDatos();

    private IAutorRepository autorRepository;
    private ILibroRepository libroRepository;

    public  Principal(IAutorRepository autorRepository,ILibroRepository libroRepository ){
        this.autorRepository=autorRepository;
        this.libroRepository=libroRepository;



    }



    public  void mostrarMenu(){
        int opcion=-1;

       while (opcion!=0 ){
           String menu= """
                1- Buscar libro por titulo
                2- Listar libros buscados 
                3- Listar autores registrados
                4- Listar autores vivos en desterminado año
                5- Listar libros por idiomas
                0- Salir
                """;

           System.out.println(menu);
           opcion = teclado.nextInt();
           teclado.nextLine();
           switch (opcion){
               case 1:
                   buscarLibro();

                   break;
               case 2:
                   listarLibrosBuscados();
                   break;
               case 3:
                   listarAutoresRegistrados();
                   break;
               case 4:
                   listarAutoresVivosEnUnaFecha();
                   break;
               case 5:
                   listarLibrosPorIdiomas();
                   break;
               case 0:
                   System.out.println("Cerrando aplicacion");
                   break;
               default:
                   System.out.println("Opcion invalida");
           }

       }
    }

    private Respuesta getLibroPorTitulo(){
        System.out.println("Escribe el nombre del libro que quieres buscar: ");
        String nombreLibro = teclado.nextLine();
        String json=consumoAPI.obtenerDatos(URL_BASE+nombreLibro.replace(" ","+"));


        Respuesta datosLibro = convierteDatos.obtenerDatos(json, Respuesta.class);



        return datosLibro;


    }

    private  void buscarLibro(){
       Respuesta respuesta= getLibroPorTitulo();

       String nombre=respuesta.resultado().get(0).autor().get(0).nombre().toLowerCase().replace(","," ");
       Integer fechaNacimiento=respuesta.resultado().get(0).autor().get(0).fechaNacimiento();
       Integer fechaFallecimiento=respuesta.resultado().get(0).autor().get(0).fechaNacimiento();


        AutorEntity autor=new AutorEntity(nombre,fechaNacimiento,fechaFallecimiento);

        // datos de un libro

         String titulo=respuesta.resultado().get(0).titulo().toLowerCase();
         String lenguaje=respuesta.resultado().get(0).lenguajes().get(0);
         Integer descargas=respuesta.resultado().get(0).descargas();


        autorRepository.save(autor);
        LibroEntity libro = new LibroEntity(titulo, lenguaje, descargas);
        libro.setAutor(autor);

        libroRepository.save(libro);




    }
    private  void listarLibrosBuscados(){
        System.out.println("Lista de libros buscados");
        System.out.println(libroRepository.listarLibrosBuscados());

    }

    private  void listarAutoresRegistrados(){
        System.out.println("Autores registrados");
        System.out.println(autorRepository.listarAutoresRegistrados());
    }

    private  void  listarAutoresVivosEnUnaFecha(){
        System.out.println("Introduzca la fecha... ");
        Integer fecha=teclado.nextInt();

        System.out.println(autorRepository.listarAutoresVivos(fecha));
    }

    private void  listarLibrosPorIdiomas(){
        String menuIdioma= """
                elija el idioma por el cual quiere listar los libros
                en -> ingles
                es -> español
                """;
        System.out.println(menuIdioma);
        String lenguaje=teclado.nextLine().toLowerCase();
        System.out.println(libroRepository.listarLibrosPorIdiomas(lenguaje));
    }





}
