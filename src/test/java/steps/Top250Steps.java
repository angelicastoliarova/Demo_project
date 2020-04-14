package steps;

import bean.Movie;
import io.qameta.allure.Step;
import pages.Top250Page;

import java.util.ArrayList;
import java.util.List;

public class Top250Steps {
    public Top250Steps() {
    }

    @Step("Calculate avarage movie rating by year")
    public double avarageMovieRatingYear(List<Movie> listTop250Movies, int year) {
        return listTop250Movies.stream().filter(mm -> mm.getMovieYear() == year).mapToDouble(Movie::getMovieRating).summaryStatistics().getAverage();
    }

    @Step("Calculate movie voaters by voices number")
    public double calculateMovieVoatersByVoicesNumber(List<Movie> listTop250Movies, int numberOfVoices) {
        return listTop250Movies.stream().filter(movie -> movie.getMovieVoaters() > numberOfVoices).count();
    }

    @Step("get list of top 250 movies from UI ")
    public List<Movie> getTopMovies() {
        List<Movie> movies = new ArrayList<>();
        List<String> tableData = new Top250Page().getUiTopMovies();
        tableData.forEach(movieStringData -> {
            String[] msd = movieStringData.split("\n");
            int i = 0;
            String movieRusName = msd[i].replaceAll("\\(.*\\)", "").replaceAll("\\d*[.]", "");
            int movieYear = Integer.parseInt(msd[i].replaceAll("^.*\\(", "").substring(0, 4));
            String movieOrigName = "";
            if (msd.length == 3) {
                movieOrigName = msd[++i];
            }
            if (msd.length > 3) {
                i++;
            }
            i++;
            float movieRating = Float.parseFloat(msd[i].replaceAll("\\(.*\\)", ""));
            int movieVoaters = Integer.parseInt(msd[i].replaceAll("^.*\\(", "").replaceAll("\\s+", "").replaceFirst("\\)", ""));
            movies.add(
                    new Movie(movieRusName, movieYear, movieOrigName, movieRating, movieVoaters));
        });
        return movies;
    }
}
