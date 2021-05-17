package com.azizavci.imdbproject.models;

public class Movie {
    private int id;
    private String title;
    private int year;
    private int runtime;
    private double rating;
    private int totalScore;
    private String genres;
    private String[] actors;
    private String[] director;
    private String[] producer;
    private String imageUrl;

    public Movie() {
    }

    public Movie(int id, String title, int year, int runtime, float rating, int totalScore, String genres, String[] actors, String[] director, String[] producer, String imageUrl) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
        this.totalScore = totalScore;
        this.genres = genres;
        this.actors = actors;
        this.director = director;
        this.producer = producer;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getProducer() {
        return producer;
    }

    public void setProducer(String[] producer) {
        this.producer = producer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
