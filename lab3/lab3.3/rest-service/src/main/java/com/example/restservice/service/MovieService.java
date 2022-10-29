package com.example.restservice.service;

import com.example.restservice.entity.Movie;
import com.example.restservice.entity.Quote;
import com.example.restservice.repository.MovieRepository;
import com.example.restservice.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service

public class MovieService {

    private MovieRepository movie;
    private QuoteRepository quote;
    @Autowired

    public MovieService (MovieRepository movie, QuoteRepository quote) {
            this.movie = movie;
            this.quote = quote;
    }

    public Movie saveMovie(Movie movie) {
        return movie.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movie) {
        return movie.saveAll(movie);
    }

    public List<Movie> getMovies() {
        return movie.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return movie.findById(id);
    }

    public List<Movie> getMovieByTitle(String title) {
        return movie.findByTitle(title);
    }

    public List<Quote> getMovieQuotes(int id) {

        List<Quote> res = new ArrayList<>();
        List<Quote> allQuotes = getQuotes();
        int otherId;

        for (Quote quote: allQuotes) {
            otherId = (int) quote.getMovie().getId();
            if (otherId == id)
                res.add(quote);
        }

        return res;
    }

    public Quote saveQuote(Quote quote) {
        Movie movie = quote.getMovie();
        List<Movie> allMovies = movie.findAll();

        if (!allMovies.contains(movie))
            return null;

        return quote.save(quote);
    }

    public List<Quote> getQuotes() {
        return quote.findAll();
    }

    public Optional<Quote> getQuoteById(int id) {
        return quote.findById(id);
    }

    public Quote randomQuote() {
        List<Quote> allQuotes = quote.findAll();

        if (allQuotes.isEmpty())
            return null;

        Random random = new Random();
        return allQuotes.get(random.nextInt(allQuotes.size()));
    }

}
