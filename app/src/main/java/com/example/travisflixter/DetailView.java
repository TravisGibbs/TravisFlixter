package com.example.travisflixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

public class DetailView extends AppCompatActivity {
    TextView tvTitle;
    TextView tvOverview;
    TextView tvreleaseDate;
    RatingBar ratingBar;
    ImageView ivPoster;
    int radius = 20;
    int margin = 5;

    String Tag="DetailView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        tvTitle = findViewById(R.id.title);
        tvOverview = findViewById(R.id.overview);
        tvreleaseDate = findViewById(R.id.rDate);
        ratingBar = findViewById(R.id.rBar);
        ivPoster = findViewById(R.id.iView);
        Glide.with(this).load(getIntent().getStringExtra("background")).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
        double votes = (getIntent().getDoubleExtra("voteAvg",0.0)/2);
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvOverview.setText(getIntent().getStringExtra("overview"));
        tvreleaseDate.setText(getIntent().getStringExtra("releaseDate"));
        float f = (float) votes;
        ratingBar.setRating(f);



    }
}