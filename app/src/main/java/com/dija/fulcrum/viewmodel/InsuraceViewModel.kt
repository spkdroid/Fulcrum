package com.dija.fulcrum.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import com.dija.fulcrum.R
import com.dija.fulcrum.data.CarriersData

class InsuraceViewModel : ViewModel() {
    
    var carriersFilterArray: CarriersData? = null
    var carrierMasterArray: CarriersData? = null


    fun readTextFile(
        resources: Resources
    ): String {
        val inputStream = resources.openRawResource(R.raw.carriers)
            val carrierJsonString = ByteArray(inputStream.available())
            inputStream.read(carrierJsonString)
        return String(carrierJsonString)
    }

    fun clearCarrierArray(){
        carriersFilterArray!!.insuranceCarriers.clear()
    }

    fun carrierSelectedValidFlag(selectedOption:String):Boolean {
        return carriersFilterArray!!.insuranceCarriers.contains(selectedOption)
    }



}
