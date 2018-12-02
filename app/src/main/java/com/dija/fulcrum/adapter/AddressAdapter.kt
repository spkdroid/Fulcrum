package com.dija.fulcrum.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.dija.fulcrum.R
import kotlinx.android.synthetic.main.address_list_item.view.*
import android.view.MotionEvent
import android.text.method.Touch.onTouchEvent
import android.view.GestureDetector



class AddressAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>()
     {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.address_list_item, parent, false))
        return view
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        holder?.predictedAddressText?.text = items.get(position)
    }

}




class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
    // Holds the TextView that will add each animal to
    val predictedAddressText = view.predictedAddressText!!
}

interface ClickListener {
    fun onClick(view: View, position: Int)
    fun onLongClick(view: View, position: Int)
}


internal class RecyclerTouchListener(
    context: Context,
    recycleView: RecyclerView,
    private val clicklistener: ClickListener?
) : RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recycleView.findChildViewUnder(e.x, e.y)
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child = rv.findChildViewUnder(e.x, e.y)
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
            clicklistener.onClick(child, rv.getChildAdapterPosition(child))
        }

        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}


