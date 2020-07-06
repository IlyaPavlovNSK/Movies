package com.pavlovnsk.movies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.pavlovnsk.movies.AppRepository;
import com.pavlovnsk.movies.model.Movie;
import com.pavlovnsk.movies.model.Search;

import java.util.List;

public class DetailMovieViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    public DetailMovieViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public MutableLiveData<List<Search>> getMoviesList(String searchMovies) {
        return appRepository.getMoviesList(searchMovies);
    }

    public MutableLiveData<Movie> getMovie(String searchMovie) {
        return appRepository.getMovie(searchMovie);
    }
}
