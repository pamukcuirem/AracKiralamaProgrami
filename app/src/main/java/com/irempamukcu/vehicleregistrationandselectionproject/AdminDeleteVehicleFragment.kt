package com.irempamukcu.vehicleregistrationandselectionproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_admin_delete_vehicle.*

class AdminDeleteVehicleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_delete_vehicle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vehicleDeleteButton.setOnClickListener {
            deleteVehicle(it)
            val action11 = AdminDeleteVehicleFragmentDirections.actionAdminDeleteVehicleFragmentToAdminVehicleDeleteAddFragment()
            Navigation.findNavController(it).navigate(action11)
        }

    }

    fun deleteVehicle(view : View){

        val vehicleToDelete = vehicleToDeleteEditText.text.toString()


        try{
            context?.let{
                val database = it.openOrCreateDatabase("Araclar", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS araclar(id INTEGER PRIMARY KEY, aracmodeli VARCHAR, plaka VARCHAR, saglik VARCHAR, beygir VARCHAR, vitestipi VARCHAR, kackisilik VARCHAR, gunlukucret VARCHAR, aracfoto BLOB)")
                database.execSQL("DELETE FROM araclar WHERE plaka = '${vehicleToDelete}'")
            }

        }catch (e : Exception){
            e.printStackTrace()
        }


    }


}
