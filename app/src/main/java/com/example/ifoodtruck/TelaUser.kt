package com.example.ifoodtruck

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_adm.*
import kotlinx.android.synthetic.main.activity_tela_user.*

class TelaUser : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_user)

        var int = getIntent()

        mAuth = FirebaseAuth.getInstance()


        inputNomeEstabelecimentoUser.text = int.getStringExtra("nomeEstab")
        inputEspecialidadeUser.text       = int.getStringExtra("Espec")
        inputTelefoneUser.text            = int.getStringExtra("telefone")
        inputEnderecoUser.text            = int.getStringExtra("endereco")
//        var Urimagem                      =  int.getStringExtra("foto")
//
//        Picasso.with(this@TelaUser).load(Urimagem).into(imageUser)

        getDataFromFirebase ()

        btnverpratos.setOnClickListener(){
            val ListPratos = Intent (this, ListaPratos::class.java)
            startActivity(ListPratos)
        }
    }

    fun getDataFromFirebase(){

      var nome= inputNomeEstabelecimentoUser.text.toString()

//        val query = firebaseDatabase!!.getReference("FoodTruck")
        val raiz = FirebaseDatabase.getInstance().reference
        val query = raiz.child("FoodTruck").orderByChild("NomeEstabelecimento").equalTo(nome)


        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                //println(p0)
                //println("children: " + p0!!.children)
                //println("key:" + p0!!.key)
                //println("value:" + p0!!.value)
//               raiz.child().child(p0.key).

                for (snapshot in p0.children) {

                    val hashMap = snapshot.value as HashMap<String, String>

                    if (hashMap.size > 0) {

                        Picasso.with(this@TelaUser).load(hashMap["foto"]).into(imageUser);



                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

}
