package com.example.restservice;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    private Greeting[] movies = {
      new Greeting(1,"Star Wars", new String[]{"“May the Force be with you.”"}),
      new Greeting(2, "The Wizard of Oz", new String[]{"“There's no place like home.”"}),
      new Greeting(3,"Titanic", new String[]{"“I'm the king of the world!”"}),
      new Greeting(4,"Frankenstein", new String[]{"“It's alive! It's alive!” "}),
      new Greeting(5,"The Terminator", new String[]{"“I'll be back.”"}),
      new Greeting(6,"Apollo 13", new String[]{"“Houston, we have a problem.”"})
    };

    //Returns a random quote from a random show/film.

    @GetMapping("api/quote")
    public Quote quote(){
        Random random = new Random();
        Greeting movie = movies[random.nextInt(movies.length)];
        return new Quote(movie.randomQuote());
    }

    //List of all available shows (for which some quote exists).For convenience, a show should have some identifier/code.

    @GetMapping("api/shows")
    public Greeting[] quotes(){
        return movies;
    }

    // Returns a random quote for the specified show/film.
    @GetMapping("api/quotes")
        public Quote quote_3(@RequestParam(value = "show", required = true) int show){
            for (Greeting movie: movies){
                if (movie.getId() == show){
                    return new Quote(movie.randomQuote());
                }
                    //return new Quote(movie.randomQuote());
            }
            return null;
        }

    }

    //@GetMapping("/greeting")
    //public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
       // return new Greeting(counter.incrementAndGet(), String.format(template, name));
    //}

