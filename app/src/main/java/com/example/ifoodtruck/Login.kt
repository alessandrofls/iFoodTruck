package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    var mAuth : FirebaseAuth? = null
    var mAuthListening : FirebaseAuth.AuthStateListener? = null
    var int = getIntent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        mAuth= FirebaseAuth.getInstance()
        mAuthListening = FirebaseAuth.AuthStateListener { }


//        btentrar.setOnClickListener(){
//            val intTA = Intent (this, TelaAdm::class.java)
//            startActivity(intTA)
//        }


    }

    fun signin (view: View){
        mAuth!!.signInWithEmailAndPassword(userLogin.text.toString(),userSenha.text.toString())

                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val intTA = Intent (this, TelaAdm::class.java)
                        startActivity(intTA)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, R.string.MensagemErro, Toast.LENGTH_SHORT).show()
                }
    }


}
