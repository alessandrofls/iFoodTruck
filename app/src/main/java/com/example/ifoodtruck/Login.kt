package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

            var int = getIntent()

        btentrar.setOnClickListener(){
            val intTA = Intent (this, TelaAdm::class.java)
            startActivity(intTA)
        }


    }
}
