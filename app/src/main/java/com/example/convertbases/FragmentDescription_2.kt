package com.example.convertbases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.convertbases.databinding.FragmentDescription2Binding

class FragmentDescription_2 : Fragment() {

    //Create binding
    lateinit var binding: FragmentDescription2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescription2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set animation on image view
        binding.imgDescription2.animate().alpha(1f).duration = 2000
    }
}