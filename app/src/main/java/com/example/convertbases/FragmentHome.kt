package com.example.convertbases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.convertbases.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {

    //Create binding
    lateinit var binding: FragmentHomeBinding

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

        //get value from edit text first and second basis and edit text get number
        val firstBasis = binding.etFirstBasis.text
        val secondBasis = binding.etSecondBasis.text
        var number = binding.etNumber.text

        //click on convert button
        binding.btnConvert.setOnClickListener {
            if (firstBasis.isNotEmpty() && secondBasis.isNotEmpty() && number.isNotEmpty()) {
                if (firstBasis.toString().toInt() in 2..16 && secondBasis.toString()
                        .toInt() in 2..16
                ) {

                } else {
                    Toast.makeText(context, "مبناهایی که میتوانید تبدیل کنید ۲ تا ۱۶ است", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "لطفا مقادیر خواسته شده را وارد کنید:)", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}