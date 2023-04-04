package com.maatallah.currency_converter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
   val TAG="Mainactivity"
    private val egyptianPound= "Egyptian Pound"
    private val americanDollar= "American Dollar"
    private  val AED = "AED"
    private val  GBP ="GBP"
    private val DTN ="DTN"

    val values = mapOf(
        americanDollar to 1.0,
        egyptianPound to 15.73,
        AED to 3.67,
        GBP to 0.74,
        DTN to 3.2
    )

    lateinit var toDropDownMenu : AutoCompleteTextView
    lateinit var fromDropDownMenu : AutoCompleteTextView
    lateinit var convertBtn:Button
    lateinit var amountET:TextInputEditText
    lateinit var resultEt:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       initializeViews()


        populateDropDownMenu()



        convertBtn.setOnClickListener {
         calculateResult()

        }

    }
    private fun calculateResult(){
        if (amountET.text.toString().isNotEmpty()){
            val amount = amountET.text.toString().toDouble()
            val toValue= values [toDropDownMenu.text.toString()]
            val fromValue = values[fromDropDownMenu.text.toString()]
            val  result = amount.times(toValue!!.div(fromValue!!))
            resultEt.setText(result.toString())
        }else{
            amountET.setError("amount field require")


        }
    }
    private  fun populateDropDownMenu(){
        val listofCountry = listOf(egyptianPound,americanDollar,AED,GBP,DTN)
        val adapter= ArrayAdapter(this,R.layout.drop_down_list_item,listofCountry)
        toDropDownMenu.setAdapter(adapter)
        fromDropDownMenu.setAdapter(adapter)
    }
    private fun initializeViews(){
        convertBtn =findViewById(R.id.converter_button)
        amountET= findViewById(R.id.amount_edit_text)
        resultEt= findViewById(R.id.result_edit_text)
        toDropDownMenu  =findViewById(R.id.to_currency_menu)
        fromDropDownMenu =findViewById(R.id.from_currency_menu)
    }
}