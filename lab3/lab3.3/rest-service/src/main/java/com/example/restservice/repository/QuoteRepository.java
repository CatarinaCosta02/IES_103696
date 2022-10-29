package com.example.restservice.repository;

import com.example.restservice.entity.Movie;
import com.example.restservice.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {
    List<Quote> findByName(Movie movie);
}
