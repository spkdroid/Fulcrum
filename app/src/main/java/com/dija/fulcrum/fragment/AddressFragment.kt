package com.dija.fulcrum.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.dija.fulcrum.R
import com.dija.fulcrum.adapter.AddressAdapter
import com.dija.fulcrum.adapter.ClickListener
import com.dija.fulcrum.adapter.RecyclerTouchListener
import com.dija.fulcrum.viewmodel.AddressViewModel
import kotlinx.android.synthetic.main.address_fragment.*


class AddressFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {

        if(viewModel.validationFlag && autoCompleteTextView.text.isNotEmpty()) {
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

            if (view != null) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_insuraceFragment)
            }
        }
        else {
            Toast.makeText(context, "Failure", Toast.LENGTH_LONG).show()
        }
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
        suggestionList.adapter = AddressAdapter(viewModel.address, requireContext())

        suggestionList.addOnItemTouchListener(
            RecyclerTouchListener(
                this!!.activity!!,
                suggestionList, object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        autoCompleteTextView.setText(viewModel.address[position])
                        viewModel.validationFlag = true
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                })
        )

        autoCompleteTextView?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchString: Editable?) {
                viewModel.clearAddress()
                viewModel.loadAddressPrediction(searchString.toString(), context!!, suggestionList)
                viewModel.validationFlag = false
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        nextbutton.setOnClickListener(this)
    }
}