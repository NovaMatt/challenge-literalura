package com.example.literalura.repository;


import com.example.literalura.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAutorRepository extends  JpaRepository<AutorEntity,Long> {

    @Query("SELECT a.nombre FROM AutorEntity a ORDER BY a.nombre")
    List<String> listarAutoresRegistrados();

    @Query("SELECT a FROM AutorEntity a WHERE a.fechaNacimiento<:fecha AND fechaFallecimiento<:fecha")
    List<AutorEntity> listarAutoresVivos(Integer fecha);





}
