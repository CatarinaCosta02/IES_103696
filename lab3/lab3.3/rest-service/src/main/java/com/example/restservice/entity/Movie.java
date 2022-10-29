package com.example.restservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")


public class Movie {

    @Id
    @GeneratedValue
    private long id;
    private String title;

    //private final String[] quotes;

    private int year;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public int getYear() {
        return year;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", content='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
