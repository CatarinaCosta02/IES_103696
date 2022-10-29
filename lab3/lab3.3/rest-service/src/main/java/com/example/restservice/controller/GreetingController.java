package com.example.restservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.restservice.entity.Movie;
import com.example.restservice.entity.Quote;
import com.example.restservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();


    private MovieService service;

    @Autowired
    public GreetingController(MovieService service) {
        this.service = service;
    }

    @PostMapping("/movie")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @GetMapping("/movie")
    public List<Movie> movie() {
        return service.getMovies();
    }

    @GetMapping("/movie/search")
    public List<Movie> getMovieByTitle(@RequestParam(value = "title", required = true) String title) {
        return service.getMovieByTitle(title);
    }

    @GetMapping("/movie/{id}")
    public Optional<Movie> getMovieById(@PathVariable int id) {
        return service.getMovieById(id);
    }

    @GetMapping("/quotes/{id}")
    public Optional<Quote> getQuoteById(@PathVariable int id) {
        return service.getQuoteById(id);
    }

    @GetMapping("/quotes")
    public List<Quote> quotes() {
        return service.getQuotes();
    }

    @PostMapping("/quotes")
    public Quote addQuote(@RequestBody Quote quote) {
        return service.saveQuote(quote);
    }

    //@DeleteMapping("/delete/{id}")
    //public String deleteProduct(@PathVariable int id) {
        //return service.deleteProduct(id);
    //}

    @GetMapping("/quotes/random")
    public Quote randomQuote() {
        return service.randomQuote();
    }

    @GetMapping("/movies/{id}/quotes")
    public List<Quote> getMovieQuotes(@PathVariable int id) {
        return service.getMovieQuotes(id);
    }

    }

    //@GetMapping("/greeting")
    //public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
       // return new Greeting(counter.incrementAndGet(), String.format(template, name));
    //}

