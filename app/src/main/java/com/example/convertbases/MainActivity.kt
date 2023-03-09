package com.example.convertbases


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.convertbases.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create welcome toast
        Toast.makeText(this, "به اپلیکیشن تبدیل مبناها خوش آمدید", Toast.LENGTH_SHORT).show()

        //adapter
        val mainAdapter = ViewPageMainAdapter(this)
        //set adapter to binding
        binding.viewPageMain.adapter = mainAdapter
    }
}