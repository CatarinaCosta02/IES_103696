package com.example.restservice;

import java.util.Random;

public class Greeting {
    private final long id;
    private final String content;

    private final String[] quotes;

    public Greeting(long id, String content, String[] quotes) {
        this.id = id;
        this.content = content;
        this.quotes = quotes;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String[] getQuotes() {
        return quotes;
    }

    public String randomQuote(){
        Random random = new Random();
        return quotes[random.nextInt(quotes.length)];
    }

    @Override
    public String toString() {
        return "Movie" + content + "\n" + "Quote" + quotes;
    }
}
