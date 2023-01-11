package com.irempamukcu.vehicleregistrationandselectionproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_verify_vehicle_for_customer.*

class VerifyVehicleForCustomer : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_vehicle_for_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let{
            var start = VerifyVehicleForCustomerArgs.fromBundle(it).startRentDate
            var finish = VerifyVehicleForCustomerArgs.fromBundle(it).finishRentDate
            var licensePlate = VerifyVehicleForCustomerArgs.fromBundle(it).licensePlateArgs
            var price = VerifyVehicleForCustomerArgs.fromBundle(it).priceArgs
            var office = VerifyVehicleForCustomerArgs.fromBundle(it).officeArgs



            rentVehicleView.text = "Araç Plakası:\n" + licensePlate
            rentDateView.text = "Tarih:\n" + start + "-" + finish
            payView.text = "Günlük Ücret:\n" + price + " ₺"
            officeView.text = "Arabanın Alınacağı Ofis:\n" + office

            context?.let{
                val database = it.openOrCreateDatabase("Arabalar", Context.MODE_PRIVATE,null)
                database.execSQL("CREATE TABLE IF NOT EXISTS arabalar(id INTEGER PRIMARY KEY, aracmodeli VARCHAR, plaka VARCHAR, saglik VARCHAR, beygir VARCHAR, vitestipi VARCHAR, kackisilik VARCHAR, gunlukucret VARCHAR, musaitmi VARCHAR, aracfoto BLOB)")
                database.execSQL("UPDATE arabalar SET musaitmi = 'Müsait değil' WHERE plaka = '${licensePlate}'")
            }


        }
        verifyButton.setOnClickListener {
            val action25 = VerifyVehicleForCustomerDirections.actionVerifyVehicleForCustomerToPaymentFragment()
            Navigation.findNavController(it).navigate(action25)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}