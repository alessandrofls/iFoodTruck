package com.example.ifoodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ifoodtruck.Beans.FoodTruck
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_adm.*

class TelaAdm : AppCompatActivity() {

    var firebaseDatabase: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_adm)

        var int = getIntent()

        mAuth = FirebaseAuth.getInstance()
        getDataFromFirebase ()



        btcadastrarPratos.setOnClickListener(){
            val intCP = Intent (this, Activity_cadastro_pratos::class.java)
            startActivity(intCP)
        }

        btnUpdate.setOnClickListener(){
            val intUpdate = Intent (this, activity_cadastro_foodtruck::class.java)
            startActivity(intUpdate)
        }
        btnlstpratos.setOnClickListener(){
            val ListPratos = Intent (this, ListaPratos::class.java)
            startActivity(ListPratos)
        }
    }
    fun getDataFromFirebase(){

        var serAtual= mAuth!!.currentUser!!.email.toString()

//        val query = firebaseDatabase!!.getReference("FoodTruck")
      val raiz = FirebaseDatabase.getInstance().reference
        val query = raiz.child("FoodTruck").orderByChild("login").equalTo(serAtual)


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
                        inputNomeEstabelecimento.text= hashMap["nomeEstabelecimento"]


                        inputNomeProprietario.text = hashMap["NomeProprietario"]
                        inputTelefone.text= hashMap["telefone"]
                        inputEndereco.text = hashMap["endereco"]
                        inputEspecialidade.text=  hashMap["especialidade"]

                        Picasso.with(this@TelaAdm).load(hashMap["foto"]).into(imageAdm);



                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

}


