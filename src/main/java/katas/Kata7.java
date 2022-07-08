package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

         List<Map> result = movieLists.stream()
                 .map(element->element.getVideos())
                 .flatMap(c -> c.stream())
                .map(element ->{
                    return ImmutableMap.of("id", element.getId(),
                            "title", element.getTitle(),
                            "boxart", element.getBoxarts().stream()
                                    .reduce((elementPreview, elementCurrent)->(elementPreview.getWidth()<elementCurrent.getWidth()?elementPreview:elementCurrent)));

                }).collect(Collectors.toList());

        return result;
    }
}
