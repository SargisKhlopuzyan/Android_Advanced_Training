package com.example.rest_api_using_retrofit_library.rest;

import com.example.rest_api_using_retrofit_library.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sargiskh on 12/8/2017.
 */

public interface MovieApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
