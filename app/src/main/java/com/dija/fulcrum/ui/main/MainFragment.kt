package com.dija.fulcrum.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dija.fulcrum.R
import com.dija.fulcrum.Controller.RetrofitController
import com.dija.fulcrum.`interface`.PlaceAutoCompleteAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var disposable: Disposable? = null

    private val placeAutoCompleteAPI by lazy {
        PlaceAutoCompleteAPI.create()
    }

    private lateinit var retro : RetrofitController

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
//        retro = RetrofitController()
//        retro.start()
        placeAutoCompleteAPI.loadPredictions("14 Ripon Road,East York")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        val actualList = result!!.getPredictions()
                        actualList.forEach { prediction_ -> Toast.makeText(context,prediction_!!.description,Toast.LENGTH_LONG).show() }
                    }
                },
                { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
            )
    }


    }


