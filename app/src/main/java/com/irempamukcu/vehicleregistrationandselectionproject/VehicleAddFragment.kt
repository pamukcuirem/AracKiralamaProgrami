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

        //runs saveVehicle function and checks if condition when vehicleSaveButton is pressed
        vehicleSaveButton.setOnClickListener {
            saveVehicle(it)

            //moves to AdminVehicleDeleteAddFragment when isVehicleOk function is 'True'
            if(isVehicleOk(view)){

                val action9 = VehicleAddFragmentDirections.actionVehicleAddFragmentToAdminVehicleDeleteAddFragment()
                Navigation.findNavController(it).navigate(action9)
            }

        }

        //runs saveVehiclePicture function when addVehicleImage is pressed
        addVehicleImage.setOnClickListener {
            saveVehiclePicture(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    //checks the vehicleModel length is less than 30
    fun isVehicleModel(view : View) : Boolean{
        val vehicleModel = addVehicleModelEditText.text.toString()
        return vehicleModel.length < 30
    }

    //checks the vehicleHealth is equals 'iyi' or 'kötü'
    fun isVehicleHealth(view : View) : Boolean{
        val vehicleHealth = addVehicleHealthEditText.text.toString()
        return vehicleHealth.equals("iyi",ignoreCase = true) || vehicleHealth.equals("kötü",ignoreCase = true)
    }

    //checks the gearType is equals 'manuel' or 'otomatik'
    fun isgearType(view : View) : Boolean{
        val gearType = addVehicleGearTypeEditText.text.toString()
        return gearType.equals("manuel",ignoreCase = true) || gearType.equals("otomatik",ignoreCase = true)
    }

    //checks the licensePlate length is equals or less than 11
    fun isLicensePlate(view : View) : Boolean{
        val licensePlate = addVehicleLicensePlateEditText.text.toString()
        return licensePlate.length <= 11
    }

    //checks the vehiclePower length is equals or less than 4
    fun isVehiclePower(view : View) : Boolean{
        val vehiclePower = addVehiclePowerEditText.text.toString()
        return vehiclePower.length <= 4
    }

    //checks the howManyPerson length is equals or less than 2
    fun isHowManyPerson(view : View) : Boolean{
        val howManyPerson = addVehicleHowManyPersonEditText.text.toString()
        return howManyPerson.length <= 2
    }

    //checks the dailyPrice length is equals or less than 8
    fun isDailyPrice(view : View) : Boolean{
        val dailyPrice = addVehicleDailyPriceEditText.text.toString()
        return dailyPrice.length <= 8
    }

    //checks all vehicle conditions
    fun isVehicleOk(view : View) : Boolean{

        return isVehicleModel(view) && isLicensePlate(view) && isVehicleHealth(view) && isVehiclePower(view) && isgearType(view) && isHowManyPerson(view) && isDailyPrice(view) && vehicleBitmap != null
    }

    //saves vehicle information to the 'Araclar' database
    fun saveVehicle(view : View){
        val vehicleModel = addVehicleModelEditText.text.toString()
        val licensePlate = addVehicleLicensePlateEditText.text.toString()
        val vehicleHealth = addVehicleHealthEditText.text.toString()
        val vehiclePower = addVehiclePowerEditText.text.toString()
        val gearType = addVehicleGearTypeEditText.text.toString()
        val howManyPerson = addVehicleHowManyPersonEditText.text.toString()
        val dailyPrice = addVehicleDailyPriceEditText.text.toString()

        //sends a warning message when isVehicleModel function is 'False'
        if(!isVehicleModel(view)){
            context?.let {
                Toast.makeText(it,"Araç modeli 30 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
        }
    }

        //sends a warning message when isLicensePlate function is 'False'
        if(!isLicensePlate(view)){
            context?.let {
                Toast.makeText(it,"Araç modeli 11 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isVehicleHealth function is 'False'
        if(!isVehicleHealth(view)){
            context?.let {
                Toast.makeText(it,"Araç sağlığı 'iyi' ya da 'kötü' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isVehiclePower function is 'False'
        if(!isVehiclePower(view)){
            context?.let {
                Toast.makeText(it,"Araç beygiri 4 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isgearType function is 'False'
        if(!isgearType(view)){
            context?.let {
                Toast.makeText(it,"Vites tipi 'manuel' ya da 'otomatik' dışında bir değer olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isHowManyPerson function is 'False'
        if(!isHowManyPerson(view)){
            context?.let {
                Toast.makeText(it,"Kişi sayısı 2 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isDailyPrice function is 'False'
        if(!isDailyPrice(view)){
            context?.let {
                Toast.makeText(it,"Günlük ücret 8 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        //saves vehicle information to the 'Araclar' database when isVehicleOk function is 'True'
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

    //takes vehicle image
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

    //checks conditions
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

    //checks conditions
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