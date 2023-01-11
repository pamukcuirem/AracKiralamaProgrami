package com.irempamukcu.vehicleregistrationandselectionproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.customer_view_for_admin_recycler_row.view.*

class CustomerViewForAdminAdapter(val customerIdList : ArrayList<Int>, val customerNameList : ArrayList<String>, val customerSurnameList : ArrayList<String>, val customerNumberList: ArrayList<String>) : RecyclerView.Adapter<CustomerViewForAdminAdapter.CustomerViewVH>() {
    class CustomerViewVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.customer_view_for_admin_recycler_row,parent,false)
        return CustomerViewVH(itemView)

    }

    override fun onBindViewHolder(holder: CustomerViewVH, position: Int) {
        val information = customerNameList[position] + " " + customerSurnameList[position]
        holder.itemView.customerNameTextView.text = information
        holder.itemView.customerNumberTextView.text = customerNumberList[position]

    }

    override fun getItemCount(): Int {
        return customerIdList.size
    }
}