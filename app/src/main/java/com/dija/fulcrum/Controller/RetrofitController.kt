package com.dija.fulcrum.Controller
import android.os.Build
import android.support.annotation.RequiresApi
import com.dija.fulcrum.data.PlaceAutoCompleteAPI
import com.dija.fulcrum.data.Prediction
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitController : Callback<Prediction> {
    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val placeAutoCompleteAPI = retrofit.create(PlaceAutoCompleteAPI::class.java)

        val call = placeAutoCompleteAPI.loadPredictions("199 pope road")

        call.enqueue(this)

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {

        if (response.isSuccessful()) {
            val predictionList = response.body()
            val actualList = predictionList!!.getPredictions()
            actualList.forEach { prediction_ -> println(prediction_.getDescription()) }
        } else {
            System.out.println(response.errorBody())
        }

    }

    override fun onFailure(call: Call<Prediction>, t: Throwable) {
        t.printStackTrace()
    }

    companion object {
        private val BASE_URL = "https://maps.googleapis.com/maps/"
    }
}