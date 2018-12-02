package com.dija.fulcrum.ui.main

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.dija.fulcrum.service.PlaceAutoCompleteAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

     var address: ArrayList<String> = ArrayList()

    val placeAutoCompleteAPI by lazy {
        PlaceAutoCompleteAPI.create()
    }

    fun clearAddress(){
        if(address.size>0)
            address.clear()
    }

    fun loadAddressPrediction(search:String,context:Context,suggestionList: RecyclerView) {

        placeAutoCompleteAPI.loadPredictions(search)
            .subscribeOn(Schedulers.io())
            .debounce(10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        val actualList = result!!.getPredictions()
                        actualList.forEach {
                            //        prediction_ -> Toast.makeText(context,prediction_!!.description,Toast.LENGTH_SHORT).show()//predictionResult!!.add(prediction_!!.description)
                                prediction_ -> address.add(prediction_.description)
                        }
                        suggestionList.adapter!!.notifyDataSetChanged()
                    }
                },
                { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
            )
    }


}








