package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener(){
            val int = Intent (this, Login::class.java)
           startActivity(int)
        }

        btnSignup.setOnClickListener(){
            val int2 = Intent (this, activity_cadastro_foodtruck::class.java)
            int2.putExtra("tipo", "cadastrar")
            startActivity(int2)
        }
        btnSearch.setOnClickListener (){
            val intListFood = Intent (this, ListaFoodTrucks::class.java)
            startActivity(intListFood)
//            val int3 = Intent (this, TelaInicial::class.java)
//            startActivity(int3)

        }
    }
}
