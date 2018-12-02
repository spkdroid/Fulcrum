package com.dija.fulcrum.ui.main

import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.dija.fulcrum.service.PlaceAutoCompleteAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    public val placeAutoCompleteAPI by lazy {
        PlaceAutoCompleteAPI.create()
    }

    companion object {
        private var predictionResult: ArrayList<String>?=null
    }







}
