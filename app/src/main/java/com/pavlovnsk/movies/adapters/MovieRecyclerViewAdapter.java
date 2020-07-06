package com.pavlovnsk.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pavlovnsk.movies.R;
import com.pavlovnsk.movies.model.Search;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder> {

    public interface OnItemMovieClickListener {
        void onItemClick(int position);
    }

    private Context context;
    private List<Search> movies;
    private OnItemMovieClickListener onItemMovieClickListener;

    public MovieRecyclerViewAdapter(Context context, OnItemMovieClickListener onItemMovieClickListener) {
        this.context = context;
        movies = new ArrayList<>();
        this.onItemMovieClickListener = onItemMovieClickListener;
    }

    public void setMovies(List<Search> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view, onItemMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public List<Search> getMovies() {
        return movies;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView yearTextView;
        private TextView imbID;
        private OnItemMovieClickListener onItemMovieClickListener;

        public MovieViewHolder(@NonNull View itemView, OnItemMovieClickListener onItemMovieClickListener) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.IV_poster);
            titleTextView = itemView.findViewById(R.id.TV_title);
            yearTextView = itemView.findViewById(R.id.TV_year);
            imbID = itemView.findViewById(R.id.TV_imbID);
            this.onItemMovieClickListener = onItemMovieClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemMovieClickListener.onItemClick(getAdapterPosition());
        }

        private void bind(final Search movie) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getYear());
            imbID.setText("https://www.imdb.com/title/" + movie.getImdbID());
            Picasso.get().load(movie.getPoster()).fit().centerInside().into(posterImageView);
        }
    }
}
