package com.irempamukcu.vehicleregistrationandselectionproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_customer_view_for_admin.*


class CustomerViewForAdmin : Fragment() {

    var customerNameList = ArrayList<String>()
    var customerSurnameList = ArrayList<String>()
    var customerNumberList = ArrayList<String>()
    var customerIdList = ArrayList<Int>()
    private lateinit var listAdapter : CustomerViewForAdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_view_for_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        customerData()

        listAdapter = CustomerViewForAdminAdapter(customerIdList,customerNameList,customerSurnameList,customerNumberList)
        recyclerViewCostumerForAdmin.layoutManager = LinearLayoutManager(context)
        recyclerViewCostumerForAdmin.adapter = listAdapter

        super.onViewCreated(view, savedInstanceState)
    }

    fun customerData(){
        try{
            activity?.let{
                val database2 = it.openOrCreateDatabase("Musteriler", Context.MODE_PRIVATE,null)
                database2.execSQL("CREATE TABLE IF NOT EXISTS musteriler(id INTEGER PRIMARY KEY, musterisim VARCHAR, musterisoyisim VARCHAR, musterinumara VARCHAR)")
                val cursor = database2.rawQuery("SELECT * FROM musteriler",null)

                val customerIdForAdmin = cursor.getColumnIndex("id")
                val customerNameForAdmin = cursor.getColumnIndex("musterisim")
                val customerSurnameForAdmin = cursor.getColumnIndex("musterisoyisim")
                val customerNumberForAdmin = cursor.getColumnIndex("musterinumara")


                customerIdList.clear()
                customerNameList.clear()
                customerSurnameList.clear()
                customerNumberList.clear()



                while(cursor.moveToNext()){
                    customerIdList.add(cursor.getInt(customerIdForAdmin))
                    customerNameList.add(cursor.getString(customerNameForAdmin))
                    customerSurnameList.add(cursor.getString(customerSurnameForAdmin))
                    customerNumberList.add(cursor.getString(customerNumberForAdmin))
                }
                listAdapter.notifyDataSetChanged()
                cursor.close()
            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }


}