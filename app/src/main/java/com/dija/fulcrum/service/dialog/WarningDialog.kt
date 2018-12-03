package com.dija.fulcrum.service.dialog

import android.content.Context
import android.support.v7.app.AlertDialog
import com.dija.fulcrum.R

class WarningDialog:DialogService{

    override fun showWarningDialog(title: String, message: String, context: Context) {

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setTitle(title)
        alertDialog.setIcon(R.drawable.warning)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, "OK"
        ) { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }

}