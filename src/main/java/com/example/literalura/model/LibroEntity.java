package com.example.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String titulo;
    private String lenguaje;
    private Integer descargas;

    @ManyToOne
    private AutorEntity autor;





    public LibroEntity(String titulo, String lenguaje, Integer descargas) {
        this.titulo = titulo;
        this.lenguaje = lenguaje;
        this.descargas = descargas;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    public LibroEntity(){

    }

}
