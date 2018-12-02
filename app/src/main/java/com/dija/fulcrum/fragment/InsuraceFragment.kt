package com.dija.fulcrum.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dija.fulcrum.R
import com.dija.fulcrum.viewmodel.InsuraceViewModel

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
    }

}
