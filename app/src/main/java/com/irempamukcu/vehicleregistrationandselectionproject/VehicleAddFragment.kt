package com.irempamukcu.vehicleregistrationandselectionproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_vehicle_add.*

class VehicleAddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vehicleSaveButton.setOnClickListener {
            saveVehicle(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }
    fun saveVehicle(view : View){
        val vehicleModel = addVehicleModelEditText.text.toString()
        val licensePlate = addVehicleLicensePlateEditText.text.toString()
        val vehicleHealth = addVehicleHealthEditText.text.toString()
        val vehiclePower = addVehiclePowerEditText.text.toString().toInt()
        val gearType = addVehicleGearTypeEditText.text.toString()
        val howManyPerson = addVehicleHowManyPersonEditText.text.toString().toInt()
        val dailyPrice = addVehicleDailyPriceEditText.text.toString().toInt()


        if(vehicleModel.length > 30){
            context?.let {
                Toast.makeText(it,"Araç modeli 30 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
        }
    }

        if(licensePlate.length > 11){
            context?.let {
                Toast.makeText(it,"Araç modeli 11 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!(vehicleHealth.equals("iyi")) || !(vehicleHealth.equals("kötü"))){
            context?.let {
                Toast.makeText(it,"Araç sağlığı 'iyi' ya da 'kötü' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(vehiclePower > 9999){
            context?.let {
                Toast.makeText(it,"Araç beygiri 4 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!(gearType.equals("manuel")) || !(gearType.equals("otomatik"))){
            context?.let {
                Toast.makeText(it,"Vites tipi 'manuel' ya da 'otomatik' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(howManyPerson > 99){
            context?.let {
                Toast.makeText(it,"Kişi sayısı 2 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(dailyPrice > 999999){
            context?.let {
                Toast.makeText(it,"Günlük ücret 8 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }




}
}