package me.chenfuduo.networkingmodels.bean;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/6.
 */
public class BoxOfficeMovie implements Serializable {
    //电影名
    private String title;
    //年份
    private int year;
    //电影大纲 摘要
    private String synopsis;
    //海报的url  posters.thumbnail
    private String posterUrl;
    //评分  ratings.critics_score
    private int criticsScore;
    //演员表  abridged_cast
    private ArrayList<String> castList;

    //大海报url
    private String largePosterUrl;
    //评说
    private String criticsConsensus;
    private int audienceScore;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getCriticsScore() {
        return criticsScore;
    }

    public String getCastList() {
        return TextUtils.join(", ", castList);
    }


    public String getLargePosterUrl() {
        return largePosterUrl;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    // Returns a BoxOfficeMovie given the expected JSON
    // BoxOfficeMovie.fromJson(movieJsonDictionary)
    // Stores the `title`, `year`, `synopsis`, `poster` and `criticsScore`
    public static BoxOfficeMovie fromJson(JSONObject jsonObject) {
        BoxOfficeMovie b = new BoxOfficeMovie();
        try {
            // Deserialize json into object fields
            b.title = jsonObject.getString("title");
            b.year = jsonObject.getInt("year");
            b.synopsis = jsonObject.getString("synopsis");
            b.posterUrl = jsonObject.getJSONObject("posters").getString("thumbnail");
            b.criticsScore = jsonObject.getJSONObject("ratings").getInt("critics_score");
            // Construct simple array of cast names
            b.castList = new ArrayList<>();

            b.largePosterUrl = jsonObject.getJSONObject("posters").getString("detailed");
            b.criticsConsensus = jsonObject.getString("critics_consensus");
            b.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");

            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++) {
                b.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }


    // Decodes array of box office movie json results into movie model objects
    // BoxOfficeMovie.fromJson(jsonArrayOfMovies)
    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray) {
        ArrayList<BoxOfficeMovie> movies = new ArrayList<>(jsonArray.length());
        // Process each result in json array, decode and convert to movie
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject moviesJson;
            try {
                moviesJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            BoxOfficeMovie movie = BoxOfficeMovie.fromJson(moviesJson);
            if (movie != null) {
                movies.add(movie);
            }
        }

        return movies;
    }


}
