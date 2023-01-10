package com.irempamukcu.vehicleregistrationandselectionproject

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.vehicle_view_for_customer_recycler_row.view.*

class VehicleViewForCustomerAdapter(val vehicleIdList : ArrayList<Int>, val licensePlateList : ArrayList<String>, val vehicleModelList : ArrayList<String>, val gearTypeList : ArrayList<String>, val howManyPersonList : ArrayList<String>, val vehiclePowerList : ArrayList<String>, val dailyPriceList : ArrayList<String>, val availableList : ArrayList<String>, val vehicleBitmapList: ArrayList<Bitmap>) : RecyclerView.Adapter<VehicleViewForCustomerAdapter.VehicleViewForCustomerVH>() {
    class VehicleViewForCustomerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    //binds the adapter and recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewForCustomerVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.vehicle_view_for_customer_recycler_row, parent, false)
        return VehicleViewForCustomerVH(itemView)
    }

    //organizes what is going to be in the rows
    override fun onBindViewHolder(holder: VehicleViewForCustomerVH, position: Int) {

        val information = "Model: " + vehicleModelList[position] + "\nÖzellikler: " + gearTypeList[position] + "\n" + howManyPersonList[position] + " kişilik " + vehiclePowerList[position] + " HP " + "\nGünlük Fiyat: " + dailyPriceList[position] + "₺\n" + availableList[position]
        holder.itemView.vehicleInformationForCustomer.text = information

        holder.itemView.vehicleImageCustomerView.setImageBitmap(vehicleBitmapList[position])


        holder.itemView.pickVehicleButton.setOnClickListener {
            val action13 = VehicleViewForCustomerDirections.actionVehicleViewForCustomerToRentDateFragment(licensePlateList[position],dailyPriceList[position])
            Navigation.findNavController(it).navigate(action13)
        }

        if(availableList[position].equals("Müsait değil",true)){
            holder.itemView.pickVehicleButton.visibility = View.INVISIBLE
        }



    }

    //takes the number of rows for creating them
    override fun getItemCount(): Int {
        return licensePlateList.size

    }
}
