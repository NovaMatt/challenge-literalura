package com.example.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ConvierteDatos implements  IConvierteDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    public  <T> T obtenerDatos(String json, Class<T> clase){
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public   String convetirToJson( Map clase){
        try {
            return objectMapper.writeValueAsString(clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
