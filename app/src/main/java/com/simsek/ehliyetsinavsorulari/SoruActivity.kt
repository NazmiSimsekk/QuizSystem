package com.simsek.ehliyetsinavsorulari

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.simsek.ehliyetsinavsorulari.databinding.ActivitySoruBinding
import org.json.JSONArray
import org.json.JSONObject

class SoruActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySoruBinding
    private var dogruSkor = 0
    private var soruSayisi = 0
    private lateinit var sorularArray: JSONArray
    private lateinit var dogruCevap: String
    private var secilmisText = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoruBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        cevaplar()
        secenekCekme()
        soruGoruntule()

        if (soruSayisi == 0){
            binding.oncekiButton.visibility = View.INVISIBLE
        }
    }

    fun oncekiSoru(view: View) {
        temizleme()
        soruSayisi -= 1
        if (soruSayisi == 0){
            binding.oncekiButton.visibility = View.INVISIBLE
        }
        if (secilmisText[soruSayisi] == 6){
            temizleme()
        }
        soruGoruntule()
        cevaplar()
        secenekCekme()
    }
    @SuppressLint("SetTextI18n")
    fun sonrakiSoru(view: View) {
        temizleme()

        if (soruSayisi < sorularArray.length()){
            binding.oncekiButton.visibility = View.VISIBLE
            soruSayisi += 1

            if (soruSayisi  > secilmisText.size){
                secilmisText.add(soruSayisi -1, 6)
            }
            soruGoruntule()
            cevaplar()
            secenekCekme()

        }
        if (soruSayisi + 1 == sorularArray.length()){
            binding.sonrakiButton.text = "Bitir"
            if (binding.sonrakiButton.text.toString() == "Bitir"){
                soruSayisi -= 1

                binding.sonrakiButton.setOnClickListener {
                    val alert = AlertDialog.Builder(this)
                    alert.setTitle("Sınavı Bitirmek İstiyor musun?")
                    alert.setMessage("Doğru Cevap: $dogruSkor")
                    alert.setPositiveButton("Bitir", object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val goMainIntent = Intent(this@SoruActivity, MainActivity::class.java)
                            finish()
                            startActivity(goMainIntent)
                        }
                    })
                    alert.setNegativeButton("Sınava Devam Et",object : DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            dialog!!.dismiss()
                        }
                    })
                    alert.show()
                }
            }
        }
    }
    private fun soruGoruntule(){

        val jsonString = applicationContext.assets.open("sorular.json").bufferedReader().use {
            it.readText()
        }

        val jsonObject = JSONObject(jsonString)
        sorularArray = jsonObject.getJSONArray("sorular")

        // İlk soruyu görüntüle
        val ilkSoru = sorularArray.getJSONObject(soruSayisi)
        val soruMetni = ilkSoru.getString("soru_metni")
        val seceneklerArray = ilkSoru.getJSONArray("secenekler")
        dogruCevap = ilkSoru.getString("dogru_cevap")


        binding.soruText.text= soruMetni

        val secenekler = StringBuilder()

        binding.cevapButton1.text = seceneklerArray[0].toString()
        binding.cevapButton2.text = seceneklerArray[1].toString()
        binding.cevapButton3.text = seceneklerArray[2].toString()
        binding.cevapButton4.text = seceneklerArray[3].toString()
        binding.cevapButton5.text = seceneklerArray[4].toString()


        secenekler.append("\nDoğru Cevap: $dogruCevap")
    }

    private fun cevaplar(){

        binding.cevapButton1.setOnClickListener {
            binding.cevapButton1.setBackgroundColor(Color.GREEN)
            binding.cevapButton2.setBackgroundColor(Color.WHITE)
            binding.cevapButton3.setBackgroundColor(Color.WHITE)
            binding.cevapButton4.setBackgroundColor(Color.WHITE)
            binding.cevapButton5.setBackgroundColor(Color.WHITE)

            if ((soruSayisi == 0 && secilmisText.size == 0) || soruSayisi + 1 > secilmisText.size) {
                secilmisText.add(soruSayisi, 1)
            }else {
                secilmisText[soruSayisi] = 1
            }
        }

        binding.cevapButton2.setOnClickListener {

            binding.cevapButton1.setBackgroundColor(Color.WHITE)
            binding.cevapButton2.setBackgroundColor(Color.GREEN)
            binding.cevapButton3.setBackgroundColor(Color.WHITE)
            binding.cevapButton4.setBackgroundColor(Color.WHITE)
            binding.cevapButton5.setBackgroundColor(Color.WHITE)

            if ((soruSayisi == 0 && secilmisText.size == 0) || soruSayisi + 1 > secilmisText.size) {
                secilmisText.add(soruSayisi, 2)
            }else {
                secilmisText[soruSayisi] = 2
            }
        }

        binding.cevapButton3.setOnClickListener {

            binding.cevapButton1.setBackgroundColor(Color.WHITE)
            binding.cevapButton2.setBackgroundColor(Color.WHITE)
            binding.cevapButton3.setBackgroundColor(Color.GREEN)
            binding.cevapButton4.setBackgroundColor(Color.WHITE)
            binding.cevapButton5.setBackgroundColor(Color.WHITE)

            if ((soruSayisi == 0 && secilmisText.size == 0) || soruSayisi + 1 > secilmisText.size) {
                secilmisText.add(soruSayisi, 3)
            }else {
                secilmisText[soruSayisi] = 3
            }
        }

        binding.cevapButton4.setOnClickListener {

            binding.cevapButton1.setBackgroundColor(Color.WHITE)
            binding.cevapButton2.setBackgroundColor(Color.WHITE)
            binding.cevapButton3.setBackgroundColor(Color.WHITE)
            binding.cevapButton4.setBackgroundColor(Color.GREEN)
            binding.cevapButton5.setBackgroundColor(Color.WHITE)

            if ((soruSayisi == 0 && secilmisText.size == 0) || soruSayisi + 1 > secilmisText.size) {
                secilmisText.add(soruSayisi, 4)
            }else {
                secilmisText[soruSayisi] = 4
            }
        }

        binding.cevapButton5.setOnClickListener {

            binding.cevapButton1.setBackgroundColor(Color.WHITE)
            binding.cevapButton2.setBackgroundColor(Color.WHITE)
            binding.cevapButton3.setBackgroundColor(Color.WHITE)
            binding.cevapButton4.setBackgroundColor(Color.WHITE)
            binding.cevapButton5.setBackgroundColor(Color.GREEN)

            if ((soruSayisi == 0 && secilmisText.size == 0) || soruSayisi + 1 > secilmisText.size) {
                secilmisText.add(soruSayisi, 5)
            }else {
                secilmisText[soruSayisi] = 5
            }
        }
    }
    private fun secenekCekme(){

        if (soruSayisi + 1 > secilmisText.size){
            temizleme()
        }else if (soruSayisi + 1 <= secilmisText.size){
            if (secilmisText[soruSayisi] == 1){
                binding.cevapButton1.setBackgroundColor(Color.GREEN)
                binding.cevapButton1.isChecked = true
            }else if (secilmisText[soruSayisi] == 2){
                binding.cevapButton2.setBackgroundColor(Color.GREEN)
                binding.cevapButton2.isChecked = true
            }else if (secilmisText[soruSayisi] == 3){
                binding.cevapButton3.setBackgroundColor(Color.GREEN)
                binding.cevapButton3.isChecked = true
            }else if (secilmisText[soruSayisi] == 4){
                binding.cevapButton4.setBackgroundColor(Color.GREEN)
                binding.cevapButton4.isChecked = true
            }else if (secilmisText[soruSayisi] == 5) {
                binding.cevapButton5.setBackgroundColor(Color.GREEN)
                binding.cevapButton5.isChecked = true
            }
        }
    }

    private fun temizleme(){
        binding.radioGroup.clearCheck()
        binding.cevapButton1.setBackgroundColor(Color.WHITE)
        binding.cevapButton2.setBackgroundColor(Color.WHITE)
        binding.cevapButton3.setBackgroundColor(Color.WHITE)
        binding.cevapButton4.setBackgroundColor(Color.WHITE)
        binding.cevapButton5.setBackgroundColor(Color.WHITE)
    }
}