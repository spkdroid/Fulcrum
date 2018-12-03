package com.dija.fulcrum.service.dialog

import android.content.Context

interface DialogService {
    fun showWarningDialog(title:String,message:String,context:Context)

    fun showDoneDialog(title:String,message:String,context:Context)

    fun showInternetIssueDialog(title:String,message:String,context:Context)
}

