package com.irempamukcu.vehicleregistrationandselectionproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vehicle_view_for_customer_recycler_row.view.*

class VehicleViewForCustomerAdapter(val vehicleIdList : ArrayList<Int>, val licensePlateList : ArrayList<String>, val vehicleModelList : ArrayList<String>, val gearTypeList : ArrayList<String>, val howManyPersonList : ArrayList<String>, val vehiclePowerList : ArrayList<String>, val dailyPriceList : ArrayList<String>, val vehicleBitmapList: ArrayList<Bitmap>) : RecyclerView.Adapter<VehicleViewForCustomerAdapter.VehicleViewForCustomerVH>() {
    class VehicleViewForCustomerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewForCustomerVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView =
            inflater.inflate(R.layout.vehicle_view_for_customer_recycler_row, parent, false)
        return VehicleViewForCustomerVH(itemView)
    }

    override fun onBindViewHolder(holder: VehicleViewForCustomerVH, position: Int) {
        val information = "Model: " + vehicleModelList[position] + "\nÖzellikler: " + gearTypeList[position] + " " + howManyPersonList[position] + " " + vehiclePowerList[position] + "\nGünlük Fiyat: " + dailyPriceList[position]
        holder.itemView.vehicleInformationForCustomer.text = information

        holder.itemView.vehicleImageCustomerView.setImageBitmap(vehicleBitmapList[position])

        holder.itemView.pickVehicleButton.setOnClickListener {
            val action13 = VehicleViewForCustomerDirections.actionVehicleViewForCustomerToRentDateFragment()
            Navigation.findNavController(it).navigate(action13)
        }

    }

    override fun getItemCount(): Int {
        return licensePlateList.size

    }
}
