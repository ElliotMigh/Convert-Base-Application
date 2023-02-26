package com.example.convertbases


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "به اپلیکیشن تبدیل مبناها خوش آمدید", Toast.LENGTH_SHORT).show()
    }
}