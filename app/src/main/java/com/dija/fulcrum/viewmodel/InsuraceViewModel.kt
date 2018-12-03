package com.dija.fulcrum.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import com.dija.fulcrum.R
import com.dija.fulcrum.fragment.InsuraceFragment

class InsuraceViewModel : ViewModel() {

    fun readTextFile(
        resources: Resources
    ): String {
            val carrierResource = resources
            val inputStream = carrierResource.openRawResource(R.raw.carriers)
            val carrierJsonString = ByteArray(inputStream.available())
            inputStream.read(carrierJsonString)
        val a = String(carrierJsonString)
        return a
    }

    // TODO: Implement the ViewModel
}
