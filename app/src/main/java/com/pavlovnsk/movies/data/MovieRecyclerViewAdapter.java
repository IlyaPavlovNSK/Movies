package com.pavlovnsk.movies.data;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pavlovnsk.movies.R;
import com.pavlovnsk.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;


    public MovieRecyclerViewAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getYear());
        Picasso.get().load(movie.getPoster()).fit().centerInside().into(holder.posterImageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView yearTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.IV_poster);
            titleTextView = itemView.findViewById(R.id.TV_title);
            yearTextView = itemView.findViewById(R.id.TV_year);
        }
    }
}
