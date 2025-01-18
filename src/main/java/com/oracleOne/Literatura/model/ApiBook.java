package com.oracleOne.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiBook {
    @JsonAlias("title")
    private String title;
    @JsonAlias("authors")
    private List<ApiAuthor> authorList;
    @JsonAlias("languages")
    private List<String> languages;
    @JsonAlias("download_count")
    private Double downloadCount;

    public ApiBook() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ApiAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<ApiAuthor> authorList) {
        this.authorList = authorList;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return  "----------------------LIBRO-----------------------"+
                "\nTitulo: " + this.title +
                "\nLista de Autores: " + this.authorList.stream().map(ApiAuthor::getName).toList() +
                "\nIdiomas: " + String.join(", ", this.languages) +
                "\nNumero de descargas: " + this.downloadCount+
                "\n--------------------------------------------------";
    }
}
