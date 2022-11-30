package com.irempamukcu.vehicleregistrationandselectionproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_admin_or_customer_entrance.*

class AdminOrCustomerEntranceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_or_customer_entrance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //moves to VehicleViewForCustomer when customerLogInButton is pressed
        customerLogInButton.setOnClickListener {
            val action = AdminOrCustomerEntranceFragmentDirections.actionAdminOrCustomerEntranceFragmentToVehicleViewForCustomer()
            Navigation.findNavController(it).navigate(action)
        }

        //moves to AdminVehicleDeleteAddFragment when adminButton is pressed
        adminButton.setOnClickListener {
            val action2 = AdminOrCustomerEntranceFragmentDirections.actionAdminOrCustomerEntranceFragmentToAdminVehicleDeleteAddFragment()
            Navigation.findNavController(it).navigate(action2)
        }

        //moves to CustomerSignUpFragment when customerSignUpButton is pressed
        customerSignUpButton.setOnClickListener {
            val action3 = AdminOrCustomerEntranceFragmentDirections.actionAdminOrCustomerEntranceFragmentToCustomerSignUpFragment()
            Navigation.findNavController(it).navigate(action3)
        }

    }

}