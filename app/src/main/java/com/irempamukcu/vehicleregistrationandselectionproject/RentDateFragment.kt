package com.irempamukcu.vehicleregistrationandselectionproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_rent_date.*
import kotlinx.android.synthetic.main.fragment_rent_date.view.*
import kotlinx.android.synthetic.main.fragment_verify_vehicle_for_customer.*

class RentDateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rent_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            arguments?.let{
                var licensePlate = RentDateFragmentArgs.fromBundle(it).licensePlate
                var price = RentDateFragmentArgs.fromBundle(it).price





                //moves to PaymentFragment when rentButton is pressed
                rentButton.setOnClickListener {
                    val start = rentStartDate.text.toString()
                    val finish = editTextDate2.text.toString()
                    val office = pickOfficeEditText.text.toString()

                    val action8 = RentDateFragmentDirections.actionRentDateFragmentToVerifyVehicleForCustomer(start,finish,licensePlate,price,office)
                    Navigation.findNavController(it).navigate(action8)
                }

            }

        }





    }








