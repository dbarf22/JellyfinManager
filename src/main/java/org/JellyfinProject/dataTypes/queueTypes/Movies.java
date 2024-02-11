package org.JellyfinProject.dataTypes.queueTypes;

public class Movies {
    public String getMovieTitle() {
        return movieTitle;
    }

    public String getId() {
        return id;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getLength() {
        return length;
    }

    public String getAirTime() {
        return airTime;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String[] getGenres() {
        return genres;
    }

    public Movies(String id) {
        this.id = id;
    } //Movies - constructor
    public String movieTitle;
    public String id;
    int length;
    String airTime;
    public int productionYear;
    public String[] genres;
} //Movies