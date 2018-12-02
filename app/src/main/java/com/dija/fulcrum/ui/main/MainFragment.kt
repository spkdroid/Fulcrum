package com.dija.fulcrum.ui.main

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
import kotlinx.android.synthetic.main.main_fragment.*
import com.dija.fulcrum.adapter.AddressAdapter
import android.app.Activity
import com.dija.fulcrum.service.IntentServiceResult
import org.greenrobot.eventbus.EventBus
import android.content.Intent
import android.widget.AbsListView
import android.widget.TextView
import com.dija.fulcrum.adapter.ClickListener
import com.dija.fulcrum.adapter.RecyclerTouchListener


class MainFragment : Fragment() , AddressAdapter.EventHandler {

     fun onHandleIntent(intent: Intent) {
        // do some work
        EventBus.getDefault().post(IntentServiceResult(Activity.RESULT_OK, "done!!"))
    }

    override fun handle(position: Int) {
    Toast.makeText(context,Int.toString(),Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        suggestionList.layoutManager = LinearLayoutManager(context)
        suggestionList.adapter = AddressAdapter(viewModel.address, requireContext())

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
                viewModel.loadAddressPrediction(searchString.toString(), context!!,suggestionList)
            }

            override fun beforeTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(searchString: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }

    }