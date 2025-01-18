package com.oracleOne.Literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class ClassMapperService implements IClassMapperService{

     private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T map(String json, Class<T> clazz) {

        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            System.out.println("No existe informacion para mapear");
            throw new RuntimeException(e);
        }
    }
}

