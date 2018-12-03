package com.dija.fulcrum.util.network

import android.content.Context
import android.net.ConnectivityManager


class AppStatus {

    private lateinit var connectivityManager: ConnectivityManager
    private var connected = false

    val isOnline: Boolean
        get() {
            try {
                connectivityManager = context
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val networkInfo = connectivityManager.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isAvailable &&
                        networkInfo.isConnected
                return connected

            } catch (e: Exception) {
             }

            return connected
        }

    companion object {

        internal lateinit var context: Context

        private val instance = AppStatus()

        fun getInstance(ctx: Context): AppStatus {
            context = ctx.applicationContext
            return instance
        }
    }
}