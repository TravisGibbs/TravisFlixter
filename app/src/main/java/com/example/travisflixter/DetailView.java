package com.example.travisflixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.travisflixter.Models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

import static android.content.Intent.ACTION_VIEW;

public class DetailView extends AppCompatActivity {
    TextView tvTitle;
    TextView tvOverview;
    TextView tvreleaseDate;
    RatingBar ratingBar;
    ImageView ivPoster;
    int radius = 20;
    int margin = 5;
    List<Movies> movies;
    String Tag="DetailView";
    String videoKey;
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        String movieId = getIntent().getStringExtra("movieId");
        String NP_URL = "https://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";

        //finding views
        tvTitle = findViewById(R.id.title);
        tvOverview = findViewById(R.id.overview);
        tvreleaseDate = findViewById(R.id.rDate);
        ratingBar = findViewById(R.id.rBar);
        ivPoster = findViewById(R.id.iView);

        //importing data for each of the views from the info packaged from intent
        Glide.with(this).load(getIntent().getStringExtra("background")).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
        double votes = (getIntent().getDoubleExtra("voteAvg",0.0)/2);
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("overview"));
        tvreleaseDate.setText(getIntent().getStringExtra("releaseDate"));
        float f = (float) votes;
        ratingBar.setRating(f);

        //call to api for video id
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NP_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(Tag, "succ");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    videoKey = results.getJSONObject(0).getString("key");
                    System.out.println(videoKey);

                } catch (JSONException e) {
                    Log.e(Tag, "fail", e);
                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(Tag, "fail");

            }
        });





        //on click listener
        ivPoster.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                URL = "https://youtube.com/watch?v=" + videoKey;
                System.out.println(videoKey);
                System.out.println(URL);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
            }
        });


    }
}