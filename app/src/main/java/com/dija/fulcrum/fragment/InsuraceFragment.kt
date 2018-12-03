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
import com.dija.fulcrum.R
import com.dija.fulcrum.adapter.BaseAdapter
import com.dija.fulcrum.adapter.ClickListener
import com.dija.fulcrum.adapter.RecyclerTouchListener
import com.dija.fulcrum.data.CarriersData
import com.dija.fulcrum.viewmodel.InsuraceViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.insurace_fragment.*


class InsuraceFragment : Fragment() {

    companion object {
        fun newInstance() = InsuraceFragment()
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
        // TODO: Use the ViewModel

        val jsonData = viewModel.readTextFile(resources)

        val carrierArray = Gson().fromJson(jsonData, CarriersData::class.java)
        val carrierMasterData = Gson().fromJson(jsonData, CarriersData::class.java)

        InsuranceProviders.layoutManager = LinearLayoutManager(context)
        InsuranceProviders.adapter = BaseAdapter(carrierArray.insuranceCarriers as ArrayList<String>, requireContext())

        InsuranceProviders.addOnItemTouchListener(
            RecyclerTouchListener(
                this!!.activity!!,
                InsuranceProviders, object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        InsuranceInputText.setText(carrierArray.insuranceCarriers[position])
                    }

                    override fun onLongClick(view: View, position: Int) {
                    }
                })
        )

        InsuranceInputText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(searchString: Editable?) {

                carrierArray.insuranceCarriers.clear()

                carrierMasterData.insuranceCarriers.forEach {
                    if(it.contains(searchString.toString()))
                        carrierArray.insuranceCarriers.add(it)
                }

                InsuranceProviders.adapter!!.notifyDataSetChanged()
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}
