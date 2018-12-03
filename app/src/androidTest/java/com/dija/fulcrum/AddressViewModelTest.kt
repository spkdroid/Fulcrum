package com.dija.fulcrum

import android.support.test.runner.AndroidJUnit4
import com.dija.fulcrum.viewmodel.AddressViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddressViewModelTest {

    companion object {
        val viewModelInstance = AddressViewModel()
    }

    @Test
    fun clearAddressArrayTest() {
        // Context of the app under test.
       // val appContext = InstrumentationRegistry.getTargetContext()
        viewModelInstance.address.add("14 Ripon Road")
        viewModelInstance.address.add("Toronto")
        viewModelInstance.address.add("East York")

        Assert.assertEquals(viewModelInstance.address.size>0,true)
        //    Assert.assertEquals("com.dija.fulcrum", appContext.packageName)
        viewModelInstance.clearAddress()
        Assert.assertEquals(viewModelInstance.address.size,0)
    }


   @Test
   fun addressSelectedFlagTest() {

       viewModelInstance.address.add("14 Ripon Road")
       viewModelInstance.address.add("Toronto")
       viewModelInstance.address.add("East York")

       Assert.assertEquals(viewModelInstance.addressSelectedValidFlag("East York"),true)
       Assert.assertEquals(viewModelInstance.addressSelectedValidFlag("Toronto"),true)
       Assert.assertEquals(viewModelInstance.addressSelectedValidFlag("14 Ripon Road"),true)
       Assert.assertEquals(viewModelInstance.addressSelectedValidFlag("10"),false)
   }


}