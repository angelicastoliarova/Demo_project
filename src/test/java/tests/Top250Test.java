package tests;

import bean.Movie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.Top250Steps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class Top250Test extends BaseTest {
    static List<Movie> listTop250Movies = new ArrayList<>();
    Top250Steps top250Steps;

    @BeforeClass
    public void setUp() {
        open("https://www.kinopoisk.ru/top/");
        top250Steps = new Top250Steps();
    }

    @Test
    public void verifyNumberOfMovies() {
        int year_2010 = 2010;
        int year_2019 = 2019;
        double avarageMovieRatingYear_2010;
        double avarageMovieRatingYear_2019;
        listTop250Movies = top250Steps.getTopMovies();
        avarageMovieRatingYear_2010 = top250Steps.avarageMovieRatingYear(listTop250Movies, year_2010);
        avarageMovieRatingYear_2019 = top250Steps.avarageMovieRatingYear(listTop250Movies, year_2019);
        Assert.assertTrue(avarageMovieRatingYear_2010 > avarageMovieRatingYear_2019);
    }

    @Test
    public void verifyMedianRaitingOfMovies() {
        int numberOfVoices = 100000;
        int numberOfTopFilms = 30;
        listTop250Movies = top250Steps.getTopMovies();
        boolean isEqualThirty = top250Steps.calculateMovieVoatersByVoicesNumber(listTop250Movies,numberOfVoices)>= numberOfTopFilms; ;
        Assert.assertTrue(isEqualThirty);
    }
}

