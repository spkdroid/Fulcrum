package com.dija.fulcrum.dagger.component

import com.dija.fulcrum.MainActivity
import com.dija.fulcrum.dagger.NetModule
import com.dija.fulcrum.viewmodel.AddressViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = arrayOf(NetModule::class))
interface NetworkComponent {
    fun inject(activity: MainActivity)

    fun inject(addressViewModel: AddressViewModel)
}