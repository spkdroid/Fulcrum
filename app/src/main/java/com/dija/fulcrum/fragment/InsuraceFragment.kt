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
import com.dija.fulcrum.R
import com.dija.fulcrum.adapter.BaseAdapter
import com.dija.fulcrum.adapter.ClickListener
import com.dija.fulcrum.adapter.RecyclerTouchListener
import com.dija.fulcrum.service.dialog.MessageDialog
import com.dija.fulcrum.viewmodel.InsuraceViewModel
import kotlinx.android.synthetic.main.insurace_fragment.*

class InsuraceFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View?) {

        if (!(viewModel.carrierSelectedValidFlag(insuranceInputField.text.toString()) && insuranceInputField.text.isNotEmpty()))
            messageService.showWarningDialog(
                "No Option Selected",
                "Please Select an option to continue",
                (context as Activity?)!!
            )
        else
            messageService.showDoneDialog("Thank You", "Success", (context as Activity?)!!)
    }

    companion object {
        fun newInstance() = InsuraceFragment()
        var messageService = MessageDialog()
    }

    private lateinit var viewModel: InsuraceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.insurace_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(InsuraceViewModel::class.java)
  
        viewModel.LoadCarrierArray(resources)

        insuranceProviders.layoutManager = LinearLayoutManager(context)
        insuranceProviders.adapter =
                BaseAdapter(viewModel.carriersFilterArray!!.insuranceCarriers as ArrayList<String>, requireContext())

        insuranceProviders.addOnItemTouchListener(
            RecyclerTouchListener(
                this!!.activity!!,
                insuranceProviders, object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        insuranceInputField.setText(viewModel.carriersFilterArray!!.insuranceCarriers[position])
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                })
        )

        insuranceInputField?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchString: Editable?) {

                viewModel.clearCarrierArray()

                viewModel.carrierMasterArray!!.insuranceCarriers.forEach {
                    if (it.contains(searchString.toString()))
                        viewModel.carriersFilterArray!!.insuranceCarriers.add(it)
                }

                insuranceProviders.adapter!!.notifyDataSetChanged()
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        confirmInsuranceButton.setOnClickListener(this)
    }
}
