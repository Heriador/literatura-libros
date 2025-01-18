package com.oracleOne.Literatura.service;

public interface IClassMapperService {

    public <T> T map(String json, Class<T> clazz);
}
