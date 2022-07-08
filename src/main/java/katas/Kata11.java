package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Create a datastructure from the given data:

    This time we have 4 seperate arrays each containing lists, videos, boxarts, and bookmarks respectively.
    Each object has a parent id, indicating its parent.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id, title, bookmark time, and smallest boxart url.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber",
                    "time": 32432,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"
                },
                {
                    "id": 675465,
                    "title": "Fracture",
                    "time": 3534543,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard",
                    "time": 645243,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys",
                    "time": 984934,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos(), DataUtil.getBoxArts(), DataUtil.getBookmarkList()
    Output: the given datastructure
*/
public class Kata11 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();

        List<Map> result = lists.stream().map(element-> {
            List<Map> listVideos = videos.stream()
                    .filter(video -> video.get("listId").equals(element.get("id")))
                    .map(videofil -> ImmutableMap.of("id", videofil.get("id"), "title", videofil.get("title"),
                            "time", bookmarkList.stream().filter(tim -> tim.get("videoId").equals(videofil.get("id"))).reduce((elementPrevious,elementCurrent)->elementCurrent).get().get("time"),
                            "boxart", boxArts.stream().filter(art -> art.get("videoId").equals(videofil.get("id"))).reduce((x,y)->x).get().get("url"))).collect(Collectors.toList());

            return ImmutableList.of(ImmutableMap.of("name", element.get("name"), "videos", listVideos));
        }).flatMap(c->c.stream()).collect(Collectors.toList());
        result.forEach(System.out::println);
        return result;
    }
}
