package com.dija.fulcrum.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.RecyclerView
import com.dija.fulcrum.R
import com.dija.fulcrum.service.AddressAutoCompleteAPI
import com.dija.fulcrum.service.dialog.MessageDialog
import com.dija.fulcrum.util.network.AppStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AddressViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var address: ArrayList<String> = ArrayList()


    private val placeAutoCompleteAPI by lazy {
        AddressAutoCompleteAPI.create()
    }

    fun clearAddress(){
        if(address.size>0)
            address.clear()
    }

    fun addressSelectedValidFlag(selectedOption:String):Boolean {
        return address.contains(selectedOption)
    }

    @SuppressLint("CheckResult")
    fun loadAddressPrediction(search:String, context:Context, suggestionList: RecyclerView) {

        placeAutoCompleteAPI.loadPredictions(search)
            .subscribeOn(Schedulers.io())
            .debounce(10, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        val actualList = result!!.getPredictions()
                        actualList.forEach {
                                prediction_ -> address.add(prediction_.description)
                        }
                        suggestionList.adapter!!.notifyDataSetChanged()
                    }
                },
                { error ->
                    run {

                       if(!AppStatus.getInstance(context).isOnline)
                       {
                           MessageDialog().showInternetIssueDialog("Network Issue",context.getString(R.string.InternetWarningMessage),context)
                       }
                    }
                })
    }
}








