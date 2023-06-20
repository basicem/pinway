package com.example.postservice.dto;

public class FilterDTO {

    private String search;

    public FilterDTO(String search) {
        this.search = search;
    }

    public FilterDTO() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
