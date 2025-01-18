package com.oracleOne.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonAlias("results")
    private List<ApiBook> bookList;

    public ApiResponse() {
    }

    public List<ApiBook> getBookList() {
        return bookList;
    }

    public void setBookList(List<ApiBook> bookList) {
        this.bookList = bookList;
    }

}
