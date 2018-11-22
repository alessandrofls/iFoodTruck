package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tela_user.*

class TelaUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_user)

        var int = getIntent()

        inputNomeEstabelecimento.text = int.getStringExtra("nomeEstab")
        inputEspecialidade.text       = int.getStringExtra("Espec")
        inputTelefone.text = int.getStringExtra("telefone")
        inputEndereco.text       = int.getStringExtra("endereco")


        btnverpratos.setOnClickListener(){
            val ListPratos = Intent (this, ListaPratos::class.java)
            startActivity(ListPratos)
        }
    }
}
