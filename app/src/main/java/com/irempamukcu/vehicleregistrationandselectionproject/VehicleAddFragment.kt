package com.irempamukcu.vehicleregistrationandselectionproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_vehicle_add.*
import kotlinx.android.synthetic.main.fragment_vehicle_view_for_admin.*
import kotlinx.android.synthetic.main.fragment_vehicle_view_for_customer.*
import java.io.ByteArrayOutputStream
import java.sql.Blob


class VehicleAddFragment : Fragment() {

    var vehicleImage : Uri? = null
    var vehicleBitmap : Bitmap? = null

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
            if(isVehicleOk(view)){

                val action9 = VehicleAddFragmentDirections.actionVehicleAddFragmentToAdminVehicleDeleteAddFragment()
                Navigation.findNavController(it).navigate(action9)
            }

        }

        addVehicleImage.setOnClickListener {
            saveVehiclePicture(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }



    fun isVehicleModel(view : View) : Boolean{
        val vehicleModel = addVehicleModelEditText.text.toString()
        return vehicleModel.length < 30
    }

    fun isVehicleHealth(view : View) : Boolean{
        val vehicleHealth = addVehicleHealthEditText.text.toString()
        return vehicleHealth.equals("iyi",ignoreCase = true) || vehicleHealth.equals("kötü",ignoreCase = true)
    }

    fun isgearType(view : View) : Boolean{
        val gearType = addVehicleGearTypeEditText.text.toString()
        return gearType.equals("manuel",ignoreCase = true) || gearType.equals("otomatik",ignoreCase = true)
    }

    fun isLicensePlate(view : View) : Boolean{
        val licensePlate = addVehicleLicensePlateEditText.text.toString()
        return licensePlate.length <= 11
    }

    fun isVehiclePower(view : View) : Boolean{
        val vehiclePower = addVehiclePowerEditText.text.toString()
        return vehiclePower.length <= 4
    }

    fun isHowManyPerson(view : View) : Boolean{
        val howManyPerson = addVehicleHowManyPersonEditText.text.toString()
        return howManyPerson.length <= 2
    }

    fun isDailyPrice(view : View) : Boolean{
        val dailyPrice = addVehicleDailyPriceEditText.text.toString()
        return dailyPrice.length <= 8
    }

    fun isVehicleOk(view : View) : Boolean{

        return isVehicleModel(view) && isLicensePlate(view) && isVehicleHealth(view) && isVehiclePower(view) && isgearType(view) && isHowManyPerson(view) && isDailyPrice(view) && vehicleBitmap != null
    }

    fun saveVehicle(view : View){
        val vehicleModel = addVehicleModelEditText.text.toString()
        val licensePlate = addVehicleLicensePlateEditText.text.toString()
        val vehicleHealth = addVehicleHealthEditText.text.toString()
        val vehiclePower = addVehiclePowerEditText.text.toString()
        val gearType = addVehicleGearTypeEditText.text.toString()
        val howManyPerson = addVehicleHowManyPersonEditText.text.toString()
        val dailyPrice = addVehicleDailyPriceEditText.text.toString()



        if(!isVehicleModel(view)){
            context?.let {
                Toast.makeText(it,"Araç modeli 30 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
        }
    }

        if(!isLicensePlate(view)){
            context?.let {
                Toast.makeText(it,"Araç modeli 11 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isVehicleHealth(view)){
            context?.let {
                Toast.makeText(it,"Araç sağlığı 'iyi' ya da 'kötü' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isVehiclePower(view)){
            context?.let {
                Toast.makeText(it,"Araç beygiri 4 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isgearType(view)){
            context?.let {
                Toast.makeText(it,"Vites tipi 'manuel' ya da 'otomatik' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isHowManyPerson(view)){
            context?.let {
                Toast.makeText(it,"Kişi sayısı 2 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isDailyPrice(view)){
            context?.let {
                Toast.makeText(it,"Günlük ücret 8 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(isVehicleOk(view)){
            val outputStream = ByteArrayOutputStream()
            vehicleBitmap!!.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val vehicleByte = outputStream.toByteArray()

            try{
                context?.let{
                    val database = it.openOrCreateDatabase("Araclar", Context.MODE_PRIVATE,null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS araclar(id INTEGER PRIMARY KEY, aracmodeli VARCHAR, plaka VARCHAR, saglik VARCHAR, beygir VARCHAR, vitestipi VARCHAR, kackisilik VARCHAR, gunlukucret VARCHAR, aracfoto BLOB)")
                    val sqlString = "INSERT INTO araclar(aracmodeli, plaka, saglik, beygir, vitestipi, kackisilik, gunlukucret, aracfoto) VALUES (?,?,?,?,?,?,?,?)"
                    val statement = database.compileStatement(sqlString)
                    statement.bindString(1,vehicleModel)
                    statement.bindString(2,licensePlate)
                    statement.bindString(3,vehicleHealth)
                    statement.bindString(4,vehiclePower)
                    statement.bindString(5,gearType)
                    statement.bindString(6,howManyPerson)
                    statement.bindString(7,dailyPrice)
                    statement.bindBlob(8,vehicleByte)
                    statement.execute()


                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun saveVehiclePicture(view : View){
        activity?.let{
            if(ContextCompat.checkSelfPermission(it.applicationContext,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else{
                val vehicleImageIntent = Intent(ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(vehicleImageIntent,2)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.size >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val vehicleImageIntent = Intent(ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(vehicleImageIntent,2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){
            vehicleImage = data.data

            try{
                context?.let{
                    if(vehicleImage != null){
                        if(Build.VERSION.SDK_INT >= 28){
                            val vehiclesource = ImageDecoder.createSource(it.contentResolver,vehicleImage!!)
                            vehicleBitmap = ImageDecoder.decodeBitmap(vehiclesource)
                            addVehicleImage.setImageBitmap(vehicleBitmap)
                        }else{
                            vehicleBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,vehicleImage)
                            addVehicleImage.setImageBitmap(vehicleBitmap)
                        }
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}