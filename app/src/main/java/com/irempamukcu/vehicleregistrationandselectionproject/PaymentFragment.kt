package com.irempamukcu.vehicleregistrationandselectionproject

import android.content.Context
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
        //runs savePayment function when paymentCompleteButton is pressed
        paymentCompleteButton.setOnClickListener {
            savePayment(it)

            //moves to AdminOrCustomerEntranceFragment when isPaymentOk function is 'True'
            if(isPaymetnOk(view)){
                val action13 = PaymentFragmentDirections.actionPaymentFragmentToAdminOrCustomerEntranceFragment()
                Navigation.findNavController(it).navigate(action13)
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    //checks the cardNumber length is equals 16
    fun isCardNumber(view : View) : Boolean{
        val cardNumber = paymentCardNumberEditText.text.toString()
        return cardNumber.length == 16
    }

    //checks the lastDateMonth length is not equals 0
    fun isLateDateMonth(view : View) : Boolean{
        val lastDateMonth = paymentCardLastDateMonthEditText.text.toString()
        return lastDateMonth.length != 0
    }

    //checks the lastDateYear length is not equals 0
    fun isLateDateYear(view : View) : Boolean{
        val lastDateYear = paymentCardLastDateYearEditText.text.toString()
        return lastDateYear.length != 0
    }

    //checks the cvv length is equals 3
    fun isCvv(view : View) : Boolean{
        val cvv = paymentCardCVVEditText.text.toString()
        return cvv.length == 3
    }

    //checks the paymentCheckBox is checked
    fun paymentBoxCheck(view: View) : Boolean{
        return paymentCheckBox.isChecked
    }

    ///checks the all card conditions
    fun isPaymetnOk(view : View) : Boolean{
        return isCardNumber(view) && isLateDateMonth(view) && isLateDateYear(view) && isCvv(view) && paymentBoxCheck(view)
    }


    fun savePayment(view : View){

        //sends a warning message when isCardNumber function is 'False'
        if(!isCardNumber(view)){
            context?.let{
                Toast.makeText(it,"Kart numarası 16 rakam olmalıdır.",Toast.LENGTH_LONG).show()
            }

        }

        //sends a warning message when isLateDateMonth function is 'False'
        if(!isLateDateMonth(view)){
            context?.let{
                Toast.makeText(it,"Bu alan boş bırakılamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isLateDateYear function is 'False'
        if(!isLateDateYear(view)){
            context?.let{
                Toast.makeText(it,"Bu alan boş bırakılamaz",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when isCvv function is 'False'
        if(!isCvv(view)){
            context?.let{
                Toast.makeText(it,"CVV 3 rakamdan oluşmalıdır",Toast.LENGTH_LONG).show()
            }
        }

        //sends a warning message when paymentBoxCheck function is 'False'
       if(!paymentBoxCheck(view)){
           context?.let{
               Toast.makeText(it,"Lütfen gerekli alanları doldurunuz",Toast.LENGTH_LONG).show()
           }
       }
    }
}