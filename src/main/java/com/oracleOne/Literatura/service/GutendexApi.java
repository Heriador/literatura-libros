package com.oracleOne.Literatura.service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexApi {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient client = HttpClient.newHttpClient();

    public String getBookByTitle(String title) {

        String url = BASE_URL + "?search=" + title;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            return response.body();
        }catch (IOException | InterruptedException e){
            throw new RuntimeException("Error while trying to get book by title");
        }
    }

}
