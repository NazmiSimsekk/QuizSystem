package com.simsek.ehliyetsinavsorulari

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.simsek.ehliyetsinavsorulari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
    fun gecisYap(view: View){
        val denemeIntent = Intent(this,SoruActivity::class.java)
        onPause()
        startActivity(denemeIntent)
    }
}