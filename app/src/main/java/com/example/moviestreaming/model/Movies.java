package com.example.moviestreaming.model;

public class Movies {
    private String title;
    private String poster;
    private String type;
    private String imdbRating;
    private String runtime;
    private String year;
    private String plot;
    private String genre;
    private String type_app;
    private String company;
    private String video;
    private String mylist;


    private int image_i;

    public Movies() {
    }

    public Movies(String title, String poster, String type, String imdbRating, String runtime, String year, String plot, String genre, String type_app, String company, String video, String mylist) {
        this.title = title;
        this.poster = poster;
        this.type = type;
        this.imdbRating = imdbRating;
        this.runtime = runtime;
        this.year = year;
        this.plot = plot;
        this.genre = genre;
        this.type_app = type_app;
        this.company = company;
        this.video = video;
        this.mylist = mylist;
    }


    public Movies(String title, int image_i) {
        this.title = title;
        this.image_i = image_i;
    }

    public Movies(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType_app() {
        return type_app;
    }

    public int getImage_i() {
        return image_i;
    }

    public void setImage_i(int image_i) {
        this.image_i = image_i;
    }

    public void setType_app(String type_app) {
        this.type_app = type_app;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String isMylist() {
        return mylist;
    }

    public void setMylist(String mylist) {
        this.mylist = mylist;
    }
}
