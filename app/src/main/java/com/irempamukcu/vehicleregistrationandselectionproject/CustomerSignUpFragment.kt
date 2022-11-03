package com.irempamukcu.vehicleregistrationandselectionproject

import android.Manifest
import android.app.Activity
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
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_customer_sign_up.*

class CustomerSignUpFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        customerCompleteSignUpButton.setOnClickListener {
            musteriKayit(it)

        }

        addIdentityButton.setOnClickListener {
            addIdentity(it)
        }

        addDrivingLicenseButton.setOnClickListener {
            addDrivingLicense(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun musteriKayit(view : View){
        val customerName = customerSignUpNameEditText.text.toString()
        val customerSurname = customerSignUpSurnameEditText.text.toString()
        val customerPhoneNumber = customerSignUpPhoneNumberEditText.text.toString()

        if(customerName.length > 15){
            context?.let {
                Toast.makeText(it,"İsim 15 karakterden fazla olamaz", Toast.LENGTH_LONG).show()
            }
        }
        if(customerSurname.length > 15){
            context?.let {
                Toast.makeText(it,"Soyisim 15 karakterden fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(customerPhoneNumber. length > 11){
            context?.let {
                Toast.makeText(it,"Telefon numarası 11 rakamdan fazla olamaz",Toast.LENGTH_LONG).show()
            }
        }

        if(customerName.length <= 15 && customerSurname.length <= 15 && customerPhoneNumber. length <= 11){
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


    }

    fun addDrivingLicense(view: View){


    }





}