package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();
        Optional<Movie> result = movies.stream().reduce((elementPreview, elementCurrent)->(elementPreview.getRating()>elementCurrent.getRating()?elementPreview:elementCurrent));
        return result.get().getRating();
    }
}
