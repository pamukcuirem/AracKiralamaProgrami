package com.irempamukcu.vehicleregistrationandselectionproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_vehicle_view_for_admin.*
import java.sql.Blob

class VehicleViewForAdmin : Fragment() {

    var vehicleIdList = ArrayList<Int>()
    var vehicleModelList = ArrayList<String>()
    var vehicleHealthList = ArrayList<String>()
    var  gearTypeList = ArrayList<String>()
    var  licensePlateList = ArrayList<String>()
    var vehiclePowerList = ArrayList<String>()
    var  howManyPersonList = ArrayList<String>()
    var dailyPriceList = ArrayList<String>()
    var vehicleBitmapList = ArrayList<Bitmap>()
    private lateinit var listAdapter :VehicleViewForAdminAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_view_for_admin, container, false)
    }

    //runs sqlData function and creates a listAdapter for showing the data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sqlData()

        listAdapter = VehicleViewForAdminAdapter(vehicleIdList,vehicleModelList,licensePlateList,vehicleHealthList,vehicleBitmapList)
        recyclerViewforAdmin.layoutManager = LinearLayoutManager(context)
        recyclerViewforAdmin.adapter = listAdapter

        super.onViewCreated(view, savedInstanceState)

    }

    //takes data from 'Araclar' database and adds them to their lists
    fun sqlData(){
        try{
            activity?.let{
                val database = it.openOrCreateDatabase("Araclar",Context.MODE_PRIVATE,null)
                val cursor = database.rawQuery("SELECT * FROM araclar",null)
                val vehicleIdIndex = cursor.getColumnIndex("id")
                val vehicleModelIndex = cursor.getColumnIndex("aracmodeli")
                val vehicleHealthIndex = cursor.getColumnIndex("saglik")
                val vehiclePowerIndex = cursor.getColumnIndex("beygir")
                val gearTypeIndex = cursor.getColumnIndex("vitestipi")
                val howManyPersonIndex = cursor.getColumnIndex("kackisilik")
                val dailyPriceIndex = cursor.getColumnIndex("gunlukucret")
                val vehicleBlobIndex = cursor.getColumnIndex("aracfoto")
                val licensePlateIndex = cursor.getColumnIndex("plaka")

                vehicleIdList.clear()
                vehicleModelList.clear()
                vehicleHealthList.clear()
                vehiclePowerList.clear()
                licensePlateList.clear()
                gearTypeList.clear()
                howManyPersonList.clear()
                dailyPriceList.clear()
                vehicleBitmapList.clear()

                while(cursor.moveToNext()){
                    vehicleIdList.add(cursor.getInt(vehicleIdIndex))
                    vehicleModelList.add(cursor.getString(vehicleModelIndex))
                    vehicleHealthList.add(cursor.getString(vehicleHealthIndex))
                    vehiclePowerList.add(cursor.getString(vehiclePowerIndex))
                    licensePlateList.add(cursor.getString(licensePlateIndex))
                    gearTypeList.add(cursor.getString(gearTypeIndex))
                    howManyPersonList.add(cursor.getString(howManyPersonIndex))
                    dailyPriceList.add(cursor.getString(dailyPriceIndex))

                    val myByteArray = cursor.getBlob(vehicleBlobIndex)
                    val myBitmap = BitmapFactory.decodeByteArray(myByteArray,0,myByteArray.size)

                    vehicleBitmapList.add(myBitmap)
                }
                listAdapter.notifyDataSetChanged()
                cursor.close()
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
/////////////////////////////////////////////////////////////////////////////////
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
    }

}