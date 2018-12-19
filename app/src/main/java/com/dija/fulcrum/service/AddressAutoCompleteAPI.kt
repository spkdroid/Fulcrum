package com.dija.fulcrum.service

import com.dija.fulcrum.data.Prediction
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressAutoCompleteAPI {

    @GET("api/place/autocomplete/json?types=address&key=AIzaSyBnMJjJXi3cyIVxzhdlYyaCG3PPQ4huF78")
    fun loadPredictions(@Query("input") address: String): Observable<Prediction>

}
