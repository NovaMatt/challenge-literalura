package com.example.literalura.repository;

import com.example.literalura.model.Libro;
import com.example.literalura.model.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository<LibroEntity,Long> {

    @Query("SELECT l.titulo FROM LibroEntity l ORDER BY l.titulo")
     List<String> listarLibrosBuscados();

    @Query("SELECT l.titulo FROM LibroEntity l WHERE l.lenguaje=:lenguaje")
    List<String> listarLibrosPorIdiomas(String lenguaje);
}
