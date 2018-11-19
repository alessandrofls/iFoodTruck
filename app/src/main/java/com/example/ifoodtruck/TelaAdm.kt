package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela_adm.*

class TelaAdm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_adm)

        var int = getIntent()

        btcadastrarPratos.setOnClickListener(){
            val intCP = Intent (this, Activity_cadastro_pratos::class.java)
            startActivity(intCP)
        }
    }
}
