package com.example.ifoodtruck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_pratos.*

class Activity_cadastro_pratos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pratos)

        var int = getIntent()

        btnCadastrarPrato.setOnClickListener(){
            Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()
        }
    }
}
