package com.irempamukcu.vehicleregistrationandselectionproject

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_vehicle_view_for_admin.*
import kotlinx.android.synthetic.main.fragment_vehicle_view_for_customer.*
import kotlinx.android.synthetic.main.vehicle_view_for_customer_recycler_row.*

class VehicleViewForCustomer : Fragment() {

    var vehicleIdList = ArrayList<Int>()
    var vehicleModelList = ArrayList<String>()
    var vehicleHealthList = ArrayList<String>()
    var  gearTypeList = ArrayList<String>()
    var  licensePlateList = ArrayList<String>()
    var vehiclePowerList = ArrayList<String>()
    var  howManyPersonList = ArrayList<String>()
    var dailyPriceList = ArrayList<String>()
    var availableList = ArrayList<String>()
    var vehicleBitmapList = ArrayList<Bitmap>()
    private lateinit var listAdapter2 :VehicleViewForCustomerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_view_for_customer, container, false)
    }

    //runs sqlData function and creates a listadapter for showing the data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sqlData2()

        listAdapter2 = VehicleViewForCustomerAdapter(vehicleIdList,licensePlateList, vehicleModelList, gearTypeList, howManyPersonList, vehiclePowerList, dailyPriceList, availableList, vehicleBitmapList)
        recyclerViewForCustomer.layoutManager = LinearLayoutManager(context)
        recyclerViewForCustomer.adapter = listAdapter2

        super.onViewCreated(view, savedInstanceState)

    }

    //takes data from 'Araclar' database and adds them to their lists
    fun sqlData2(){
        try{
            activity?.let{
                val database = it.openOrCreateDatabase("Arabalar", Context.MODE_PRIVATE,null)
                val cursor = database.rawQuery("SELECT * FROM arabalar",null)
                val vehicleIdIndex = cursor.getColumnIndex("id")
                val vehicleModelIndex = cursor.getColumnIndex("aracmodeli")
                val vehicleHealthIndex = cursor.getColumnIndex("saglik")
                val vehiclePowerIndex = cursor.getColumnIndex("beygir")
                val gearTypeIndex = cursor.getColumnIndex("vitestipi")
                val howManyPersonIndex = cursor.getColumnIndex("kackisilik")
                val dailyPriceIndex = cursor.getColumnIndex("gunlukucret")
                val vehicleBlobIndex = cursor.getColumnIndex("aracfoto")
                val avaliableIndex = cursor.getColumnIndex("musaitmi")
                val licensePlateIndex = cursor.getColumnIndex("plaka")

                vehicleIdList.clear()
                vehicleModelList.clear()
                vehicleHealthList.clear()
                vehiclePowerList.clear()
                licensePlateList.clear()
                gearTypeList.clear()
                howManyPersonList.clear()
                dailyPriceList.clear()
                availableList.clear()
                vehicleBitmapList.clear()

                while(cursor.moveToNext()){
                    vehicleIdList.add(cursor.getInt(vehicleIdIndex))
                    vehicleModelList.add(cursor.getString(vehicleModelIndex))
                    vehicleHealthList.add(cursor.getString(vehicleHealthIndex))
                    vehiclePowerList.add(cursor.getString(vehiclePowerIndex))
                    licensePlateList.add(cursor.getString(licensePlateIndex))
                    gearTypeList.add(cursor.getString(gearTypeIndex))
                    howManyPersonList.add(cursor.getString(howManyPersonIndex))
                    availableList.add(cursor.getString(avaliableIndex))
                    dailyPriceList.add(cursor.getString(dailyPriceIndex))

                    val myByteArray = cursor.getBlob(vehicleBlobIndex)
                    val myBitmap = BitmapFactory.decodeByteArray(myByteArray,0,myByteArray.size)

                    vehicleBitmapList.add(myBitmap)
                }
                listAdapter2.notifyDataSetChanged()
                cursor.close()
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

}