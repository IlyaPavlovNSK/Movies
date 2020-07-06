package com.pavlovnsk.movies;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.pavlovnsk.movies.model.Movie;
import com.pavlovnsk.movies.model.Movies;
import com.pavlovnsk.movies.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {

    private String apiKey = "29e4ffde";

    private MutableLiveData<Movie> detailMovie = new MutableLiveData<>();
    private Context context;
    private MutableLiveData<List<Search>> movies = new MutableLiveData<>();

    private JSONPlaceHolderApi jsonPlaceHolderApi;

    public AppRepository(Context context) {
        this.context = context;
        jsonPlaceHolderApi = NetworkService.getInstance().getJSONApi();
    }

    public MutableLiveData<List<Search>> getMoviesList(String searchMovies) {
        jsonPlaceHolderApi.getMovies(apiKey, searchMovies).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                movies.setValue(response.body().getSearch());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(context, "Error occurred while getting request!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        return movies;
    }

    public MutableLiveData<Movie> getMovie(String searchMovie) {
        jsonPlaceHolderApi.getMovie(apiKey, searchMovie).enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        detailMovie.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(context, "Error occurred while getting request!", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
        return detailMovie;
    }
}
