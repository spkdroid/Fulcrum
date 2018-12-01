package com.dija.fulcrum.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceAutoCompleteAPI
{
    @GET("api/place/autocomplete/json?types=address&key=AIzaSyBnMJjJXi3cyIVxzhdlYyaCG3PPQ4huF78")
    Call<Prediction> loadPredictions(@Query("input") String address);
}
