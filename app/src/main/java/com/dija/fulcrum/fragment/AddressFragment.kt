package com.dija.fulcrum.fragment

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dija.fulcrum.R
import com.dija.fulcrum.adapter.BaseAdapter
import com.dija.fulcrum.adapter.ClickListener
import com.dija.fulcrum.adapter.RecyclerTouchListener
import com.dija.fulcrum.viewmodel.AddressViewModel
import kotlinx.android.synthetic.main.address_fragment.*
import android.support.v7.app.AlertDialog
import com.dija.fulcrum.service.dialog.DialogService
import com.dija.fulcrum.service.dialog.WarningDialog

class AddressFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {

        if(viewModel.addressSelectedValidFlag(autoCompleteTextView.text.toString()) && autoCompleteTextView.text.isNotEmpty()) {

            if (view != null) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_insuraceFragment)
            }
        }
        else
         WarningDialog().showWarningDialog("No Option Selected","Please Select an option to continue",(context as Activity?)!!)

    }

    companion object {
        fun newInstance() = AddressFragment()
    }

    private lateinit var viewModel: AddressViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.address_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AddressViewModel::class.java)
        // TODO: Use the ViewModel
        suggestionList.layoutManager = LinearLayoutManager(context)
        suggestionList.adapter = BaseAdapter(viewModel.address, requireContext())

        suggestionList.addOnItemTouchListener(
            RecyclerTouchListener(
                this!!.activity!!,
                suggestionList, object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        autoCompleteTextView.setText(viewModel.address[position])
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                })
        )

        autoCompleteTextView?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchString: Editable?) {
                viewModel.clearAddress()
                viewModel.loadAddressPrediction(searchString.toString(), context!!, suggestionList)
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        nextbutton.setOnClickListener(this)
    }
}