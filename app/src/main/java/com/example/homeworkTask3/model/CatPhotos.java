package com.example.homeworkTask3.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.List;

public class CatPhotos {

    @PrimaryKey
    private String id;
    private String url;
    private List<Cats> breeds;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Cats> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Cats> breeds) {
        this.breeds = breeds;
    }

}
