package com.pavlovnsk.movies;

import com.pavlovnsk.movies.model.Movie;
import com.pavlovnsk.movies.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("/") //http://www.omdbapi.com/?apikey=29e4ffde&t=superman
    Call<Movie> getMovie(@Query("apikey") String apiKey,
                         @Query("i") String searchMovie);

    @GET("/") //http://omdbapi.com/?apikey=29e4ffde&s=superman
    Call<Movies> getMovies(@Query("apikey") String apiKey,
                           @Query("s") String searchMovies);
}
