package com.dija.fulcrum.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dija.fulcrum.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.concurrent.TimeUnit
import com.dija.fulcrum.adapter.AddressAdapter


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var address: ArrayList<String> = ArrayList()

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
        suggestionList.layoutManager = LinearLayoutManager(context)

        suggestionList.adapter = AddressAdapter(address, requireContext())

        autoCompleteTextView?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if(address.size>0)
                    address.clear()

                viewModel.placeAutoCompleteAPI.loadPredictions(p0.toString())
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

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })



    }


    }


