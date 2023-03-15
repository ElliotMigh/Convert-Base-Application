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
        var firstBasis = binding.etFirstBasis.text
        var secondBasis = binding.etSecondBasis.text
        var number = binding.etNumber.text

        //click on convert button
        binding.btnConvert.setOnClickListener {
            if (firstBasis.isNotEmpty() && secondBasis.isNotEmpty() && number.isNotEmpty()) {

            } else {
                Toast.makeText(context, "لطفا مقادیر خواسته شده را وارد کنید:)", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}