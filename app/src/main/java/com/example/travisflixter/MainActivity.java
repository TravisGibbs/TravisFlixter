package com.example.travisflixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.travisflixter.Adapters.MoviesAdapter;
import com.example.travisflixter.Models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String NP_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String Tag = "MainActivity";

    List<Movies> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rView = findViewById(R.id.rview);
        movies = new ArrayList<>();
        //make adapter

        MoviesAdapter.OnLongClickListener onLongClickListener = new MoviesAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                // watches from long click on title overview or image and then sends relevant data to detail view
                Toast.makeText(getApplicationContext(), "Movie Selected", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getBaseContext(),   DetailView.class);
                myIntent.putExtra("background", movies.get(position).getBackgroundPath());
                myIntent.putExtra("title", movies.get(position).getTitle());
                myIntent.putExtra("overview", movies.get(position).getOverview());
                myIntent.putExtra("releaseDate", movies.get(position).getReleaseDate());
                myIntent.putExtra("voteAvg", movies.get(position).getVoteAvg());
                myIntent.putExtra("movieId", movies.get(position).getMovieId());
                myIntent.putExtra("pop", movies.get(position).getMovieId());
                startActivity(myIntent);

            }
        };

       final MoviesAdapter moviesAdapter = new MoviesAdapter(this, movies, onLongClickListener);

        //set the adapter
        rView.setAdapter(moviesAdapter);
        //set layout manager
        rView.setLayoutManager(new LinearLayoutManager(this));



        //call to api for list of movies
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NP_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(Tag, "succ");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(Tag, "results: " + results.toString());
                    movies.addAll(Movies.fromJsonArray(results));
                    moviesAdapter.notifyDataSetChanged();
                    Log.i(Tag, "Movies" + movies.size());
                } catch (JSONException e) {
                    Log.e(Tag, "fail", e);
                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(Tag, "fail");

            }
        });

    }
}