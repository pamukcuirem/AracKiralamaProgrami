package com.irempamukcu.vehicleregistrationandselectionproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        paymentCompleteButton.setOnClickListener {
            savePayment(it)

          if(isPaymetnOk(view)){
              val action10 = PaymentFragmentDirections.actionPaymentFragmentToAdminOrCustomerEntranceFragment()
              Navigation.findNavController(it).navigate(action10)
          }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun isCardNumber(view : View) : Boolean{
        val cardNumber = paymentCardNumberEditText.text.toString()
        return cardNumber.length == 16
    }

    fun isLateDateMonth(view : View) : Boolean{
        val lastDateMonth = paymentCardLastDateMonthEditText.text.toString().toInt()
        return lastDateMonth != 0
    }

    fun isLateDateYear(view : View) : Boolean{
        val lastDateYear = paymentCardLastDateYearEditText.text.toString().toInt()
        return lastDateYear != 0
    }

    fun isCvv(view : View) : Boolean{
        val cvv = paymentCardCVVEditText.text.toString().toInt()
        return (cvv in 100..999)
    }

    fun paymentBoxCheck(view: View) : Boolean{
        return paymentCheckBox.isChecked
    }

    fun isPaymetnOk(view : View) : Boolean{
        return isCardNumber(view) && isLateDateMonth(view) && isLateDateYear(view) && isCvv(view) && paymentBoxCheck(view)
    }


    fun savePayment(view : View){


        if(!isCardNumber(view)){
            context?.let{
                Toast.makeText(it,"Kart numarası 16 rakam olmalıdır.",Toast.LENGTH_LONG).show()
            }

        }
        if(!isLateDateMonth(view)){
            context?.let{
                Toast.makeText(it,"Bu alan boş bırakılamaz",Toast.LENGTH_LONG).show()
            }
        }
        if(!isLateDateYear(view)){
            context?.let{
                Toast.makeText(it,"Bu alan boş bırakılamaz",Toast.LENGTH_LONG).show()
            }
        }
        if(!isCvv(view)){
            context?.let{
                Toast.makeText(it,"CVV 3 rakamdan oluşmalıdır",Toast.LENGTH_LONG).show()
            }
        }

       if(!paymentBoxCheck(view)){
           context?.let{
               Toast.makeText(it,"Lütfen gerekli alanları doldurunuz",Toast.LENGTH_LONG).show()
           }
       }




    }


}