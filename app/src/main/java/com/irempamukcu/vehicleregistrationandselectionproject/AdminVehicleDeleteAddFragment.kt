package com.irempamukcu.vehicleregistrationandselectionproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_admin_vehicle_delete_add.*

class AdminVehicleDeleteAddFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_vehicle_delete_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adminViewButton.setOnClickListener {
            val action4 = AdminVehicleDeleteAddFragmentDirections.actionAdminVehicleDeleteAddFragmentToVehicleViewForAdmin2()
            Navigation.findNavController(it).navigate(action4)
        }

        adminAddButton.setOnClickListener {
            val action5 = AdminVehicleDeleteAddFragmentDirections.actionAdminVehicleDeleteAddFragmentToVehicleAddFragment()
            Navigation.findNavController(it).navigate(action5)
        }

        adminDeleteButton.setOnClickListener {
            val action6 = AdminVehicleDeleteAddFragmentDirections.actionAdminVehicleDeleteAddFragmentToAdminDeleteVehicleFragment()
            Navigation.findNavController(it).navigate(action6)
        }
    }

}