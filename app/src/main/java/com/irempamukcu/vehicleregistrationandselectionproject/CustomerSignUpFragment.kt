package com.irempamukcu.vehicleregistrationandselectionproject

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_customer_sign_up.*
import kotlinx.android.synthetic.main.fragment_vehicle_add.*

class CustomerSignUpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object{
        val IMAGE_REQUEST_CODE = 100
        val IMAGE_REQUEST_CODE2 = 200
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addIdentityButton.setOnClickListener {
            addIdentity(it)
        }
        addDrivingLicenseButton.setOnClickListener {
            addDrivingLicense(it)
        }

        customerCompleteSignUpButton.setOnClickListener {
            customerSave(it)

            if(isCustomerOk(view)){
                val action11 = CustomerSignUpFragmentDirections.actionCustomerSignUpFragmentToAdminOrCustomerEntranceFragment()
                Navigation.findNavController(it).navigate(action11)
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    fun isCustomerName(view: View) : Boolean{
        val customerName = customerSignUpNameEditText.text.toString()
        return customerName.length <= 15
    }

    fun isCustomerSurname(view : View) : Boolean{
        val customerSurname = customerSignUpSurnameEditText.text.toString()
        return customerSurname.length <= 15
    }

    fun isCustomerNumber(view : View) : Boolean{
        val customerPhoneNumber = customerSignUpPhoneNumberEditText.text.toString()
        return customerPhoneNumber.length <= 11
    }

    fun isCustomerOk(view : View) : Boolean{
        return isCustomerName(view) && isCustomerSurname(view) && isCustomerNumber(view)
    }

    fun customerSave(view : View){
        val customerName = customerSignUpNameEditText.text.toString()
        val customerSurname = customerSignUpSurnameEditText.text.toString()
        val customerPhoneNumber = customerSignUpPhoneNumberEditText.text.toString()

        if(!isCustomerName(view)){
            context?.let {
                Toast.makeText(it,"İsim 15 karakterden fazla olamaz", Toast.LENGTH_LONG).show()
            }
        }
        if(!isCustomerSurname(view)){
            context?.let {
                Toast.makeText(it,"Soyisim 15 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(!isCustomerNumber(view)){
            context?.let {
                Toast.makeText(it,"Telefon numarası 11 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(isCustomerOk(view)){
            try{
               context?.let {
                   val database2 = it.openOrCreateDatabase("Musteriler",Context.MODE_PRIVATE,null)
                   database2.execSQL("CREATE TABLE IF NOT EXISTS musteriler(id INTEGER PRIMARY KEY, musterisim VARCHAR, musterisoyisim VARCHAR, musterinumara VARCHAR)")
                   val sqlString2 = "INSERT INTO musteriler(musterisim, musterisoyisim, musterinumara) VALUES (?,?,?)"
                   val statement2 = database2.compileStatement(sqlString2)
                   statement2.bindString(1,customerName)
                   statement2.bindString(2,customerSurname)
                   statement2.bindString(3,customerPhoneNumber)
                   statement2.execute()
               }
            }catch(e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun addIdentity(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_REQUEST_CODE)

    }


    fun addDrivingLicense(view: View){
        val intent2 = Intent(Intent.ACTION_PICK)
        intent2.type = "image/*"
        startActivityForResult(intent2,IMAGE_REQUEST_CODE2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            addIdentityButton.setImageURI(data?.data)
        }
        else if(requestCode == IMAGE_REQUEST_CODE2 && resultCode == RESULT_OK){
            addDrivingLicenseButton.setImageURI(data?.data)
        }
    }


}