package com.simsek.ehliyetsinavsorulari

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simsek.ehliyetsinavsorulari.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

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