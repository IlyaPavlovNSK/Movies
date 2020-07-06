package com.pavlovnsk.movies.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pavlovnsk.movies.R;
import com.pavlovnsk.movies.adapters.MovieRecyclerViewAdapter;
import com.pavlovnsk.movies.model.Movie;
import com.pavlovnsk.movies.model.Search;
import com.pavlovnsk.movies.viewmodels.DetailMovieViewModel;

import java.util.List;

public class ListFragment extends Fragment implements MovieRecyclerViewAdapter.OnItemMovieClickListener {

    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;
    private DetailMovieViewModel viewModel;

    private String query;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("query")) {
            query = bundle.getString("query");
        }

        viewModel = new ViewModelProvider(this).get(DetailMovieViewModel.class);
        viewModel.getMoviesList(query);

        recyclerView = view.findViewById(R.id.RV_list);
        adapter = new MovieRecyclerViewAdapter(getContext(), this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        observeViewModel();
        return view;
    }

    private void observeViewModel() {
        viewModel.getMoviesList(query).observe(getViewLifecycleOwner(), new Observer<List<Search>>() {
            @Override
            public void onChanged(List<Search> moviesList) {
                adapter.setMovies(moviesList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        String searchFilm = adapter.getMovies().get(position).getImdbID();

        viewModel.getMovie(searchFilm).observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).addToBackStack(null).commit();
            }
        });
    }
}
