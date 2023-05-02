package com.example.convertbases

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.convertbases.databinding.FragmentHomeBinding
import kotlin.math.pow

class FragmentHome : Fragment() {

    //Create binding
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //click on convert button (تبدیل کن)
        binding.btnConvert.setOnClickListener {
            if (binding.etFirstBasis.length() > 0 && binding.etSecondBasis.length() > 0 && binding.etNumber.length() > 0) {
                //get value from edit texes
                val firstBasis = binding.etFirstBasis.text.toString()
                val secondBasis = binding.etSecondBasis.text.toString()
                var myNumber = binding.etNumber.text.toString()
                if (firstBasis.toInt() >= 2 && firstBasis.toInt() <= 16 && secondBasis.toInt() >= 2 && secondBasis.toInt() <= 16) {
                    //convert bases :
                    var newNumber = 0
                    var i = 0
                    while (myNumber.toInt() > 0) {
                        var remainer = myNumber.toInt() % 10
                        newNumber += remainer * (firstBasis.toDouble().pow(i.toDouble()).toInt())
                        myNumber = (myNumber.toInt() / 10).toString()
                        i++
                    }
                    myNumber = newNumber.toString()
                    newNumber = 0
                    i = 1
                    while (myNumber.toInt() > 0) {
                        var remainer = myNumber.toInt() % secondBasis.toInt()
                        newNumber = (remainer * i) + newNumber
                        myNumber = (myNumber.toInt() / secondBasis.toInt()).toString()
                        i = i * 10
                    }
                    //set result on text view
                    binding.txtAnswer.text = newNumber.toString()
                } else {
                    Toast.makeText(
                        context,
                        "تبدیل مبناها باید بین ۲ تا ۱۶ باشد.دقت کن",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "لطفا موارد خواسته شده رو وارد کن دوست شیطون من :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}