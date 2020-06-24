package com.example.travisflixter.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travisflixter.MainActivity;
import com.example.travisflixter.Models.Movies;
import com.example.travisflixter.R;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    Context context;
    List<Movies> movies;

    public MoviesAdapter(MainActivity mainActivity, List<Movies> movies) {
        this.movies = movies;
        this.context = mainActivity;
    }
    // inflates layout from XML and returns to holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }
    // populating data into the item from the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at pos
        Movies movie = movies.get(position);
        //bind the movie data into the holder
        holder.bind(movie);

    }
    // return the total item count in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvOverview = itemView.findViewById(R.id.overview);
            ivPoster = itemView.findViewById(R.id.imageView);
        }

        public void bind(Movies movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;
            //when image is landscape serve background
            //when image is portrait serve poster
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
                imageURL=movie.getPosterPath();

            }
            else{
                imageURL=movie.getBackgroundPath();
            }
            int radius = 20;
            int margin = 5;
            Glide.with(context).load(imageURL).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);

        }
    }
}
