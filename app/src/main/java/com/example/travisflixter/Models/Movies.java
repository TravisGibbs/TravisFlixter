package com.example.travisflixter.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movies {
    String posterPath;
    String backgroundPath;
    String title;
    String overview;
    String releaseDate;
    String movieId;
    Double voteAvg;

    public String getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getVoteAvg() {
        return voteAvg;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackgroundPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backgroundPath);
    }

    public String getTitle() {
        return title;
    }



    public Movies(JSONObject jobj) throws JSONException {
        this.posterPath = jobj.getString("poster_path");
        this.title = jobj.getString("title");
        this.overview = jobj.getString("overview");
        this.backgroundPath = jobj.getString("backdrop_path");
        this.releaseDate = jobj.getString("release_date");
        this.voteAvg = jobj.getDouble("vote_average");
        this.movieId = String.valueOf(jobj.getInt("id"));

    }

    public static List<Movies> fromJsonArray(JSONArray jarr) throws JSONException {
        List<Movies> movies = new ArrayList<>();
        for(int i=0;i<jarr.length();i++){
            movies.add(new Movies(jarr.getJSONObject(i)));
        }
        return(movies);
    }
}
