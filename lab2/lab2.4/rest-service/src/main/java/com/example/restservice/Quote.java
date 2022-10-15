package com.example.restservice;

public class Quote {
    private final String quote;

    public Quote(String quote){
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return "Quote:" + quote;
    }
}
