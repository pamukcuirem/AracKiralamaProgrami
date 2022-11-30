package com.irempamukcu.vehicleregistrationandselectionproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vehicle_view_for_admin_recycler_row.view.*

class VehicleViewForAdminAdapter(val vehicleIdList : ArrayList<Int>, val vehicleModelList : ArrayList<String>,val licensePlateList : ArrayList<String>, val vehicleHealthList : ArrayList<String>, val vehicleBitmapList : ArrayList<Bitmap>) : RecyclerView.Adapter<VehicleViewForAdminAdapter.VehicleViewForAdminVH>(){
    class VehicleViewForAdminVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    //binds the adapter and recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewForAdminVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.vehicle_view_for_admin_recycler_row,parent,false)
        return VehicleViewForAdminVH(itemView)
    }

    //organizes what is going to be in the rows
    override fun onBindViewHolder(holder: VehicleViewForAdminVH, position: Int) {
        val information = "Model: " + vehicleModelList[position] + "\nPlaka: " + licensePlateList[position] + "\nAraç Sağlığı: " + vehicleHealthList[position]
        holder.itemView.vehicleInformationForAdmin.text = information

        holder.itemView.vehicleImageAdminView.setImageBitmap(vehicleBitmapList[position])
    }

    //takes the number of rows for creating them
    override fun getItemCount(): Int {
       return vehicleModelList.size
    }

}