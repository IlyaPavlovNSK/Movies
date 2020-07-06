package com.pavlovnsk.movies.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pavlovnsk.movies.R;
import com.pavlovnsk.movies.model.Movie;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView yearTextView;
    private TextView imbID;
    private RatingBar ratingBar;

    private Movie movie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("movie")) {
            movie = (Movie) bundle.getSerializable("movie");
        }

        posterImageView = view.findViewById(R.id.IV_poster);
        titleTextView = view.findViewById(R.id.TV_title);
        yearTextView = view.findViewById(R.id.TV_year);
        imbID = view.findViewById(R.id.TV_imbID);
        ratingBar = view.findViewById(R.id.ratingBar);

        setMovie(movie);

        return view;
    }

    private void setMovie(Movie detailMovie) {
        titleTextView.setText(detailMovie.getTitle());
        yearTextView.setText(detailMovie.getYear());
        imbID.setText(detailMovie.getImdbID());
        ratingBar.setRating(Float.parseFloat(detailMovie.getImdbRating()));
        Picasso.get().load(detailMovie.getPoster()).fit().centerInside().into(posterImageView);
    }
}
