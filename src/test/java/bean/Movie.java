package bean;

import java.util.List;

public class Movie {
    private String movieRusName;
    private int movieYear;
    private String movieOrigName;
    private float movieRating;
    private Integer movieVoaters;

    public Movie() {
    }
    public Movie(String movieRusName, int movieYear, String movieOrigName, float movieRating, int movieVoaters) {
        this.movieRusName = movieRusName;
        this.movieYear = movieYear;
        this.movieOrigName = movieOrigName;
        this.movieRating = movieRating;
        this.movieVoaters = movieVoaters;
    }
    public String getMovieRusName() {
        return movieRusName;
    }
    public void setMovieRusName(String movieRusName) {
        this.movieRusName = movieRusName;
    }
    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieOrigName() {
        return movieOrigName;
    }

    public void setMovieOrigName(String movieOrigName) {
        this.movieOrigName = movieOrigName;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }

    public Integer getMovieVoaters() {
        return movieVoaters;
    }

    public void setMovieVoaters(Integer movieVoaters) {
        this.movieVoaters = movieVoaters;
    }

    public int getMovieYear() {
        return movieYear;
    }
}
