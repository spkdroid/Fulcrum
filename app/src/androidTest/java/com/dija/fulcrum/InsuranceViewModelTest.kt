package com.dija.fulcrum

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.dija.fulcrum.viewmodel.InsuraceViewModel
import org.junit.Assert


@RunWith(AndroidJUnit4::class)
class InsuranceViewModelTest {

    companion object {
        val viewModelInstance = InsuraceViewModel()
    }

    @Test
    fun ReadJsonDataTest(){
        val appContext = InstrumentationRegistry.getTargetContext()
        val stringData = viewModelInstance.readTextFile(appContext.resources)
        Assert.assertEquals(stringData.isNullOrBlank(),false);
    }



}