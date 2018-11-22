package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class TelaInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        val int = intent

        btnlistfood.setOnClickListener(){
            val intListFood = Intent (this, ListaFoodTrucks::class.java)
            startActivity(intListFood)
        }
    }
}
