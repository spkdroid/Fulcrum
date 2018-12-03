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
import com.dija.fulcrum.service.dialog.MessageDialog

class AddressFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {

        if(viewModel.addressSelectedValidFlag(addressInputField.text.toString()) && addressInputField.text.isNotEmpty()) {

            if (view != null) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_insuraceFragment)
            }
        }
        else
         MessageDialog().showWarningDialog("No Option Selected","Please Select an option to continue",(context as Activity?)!!)

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
        addressSuggestionList.layoutManager = LinearLayoutManager(context)
        addressSuggestionList.adapter = BaseAdapter(viewModel.address, requireContext())

        addressSuggestionList.addOnItemTouchListener(
            RecyclerTouchListener(
                this!!.activity!!,
                addressSuggestionList, object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        addressInputField.setText(viewModel.address[position])
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                })
        )

        addressInputField?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchString: Editable?) {
                viewModel.clearAddress()
                viewModel.loadAddressPrediction(searchString.toString(), context!!, addressSuggestionList)
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        confirmAddressNextButton.setOnClickListener(this)
    }
}