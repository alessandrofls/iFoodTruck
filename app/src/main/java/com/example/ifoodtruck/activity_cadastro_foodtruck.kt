package com.example.ifoodtruck

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_cadastro_foodtruck.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class activity_cadastro_foodtruck : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null
    var mStorageRef: StorageReference? = null
    var selected: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_foodtruck)

        var int = getIntent()

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase!!.reference
        mStorageRef = FirebaseStorage.getInstance().reference

        btncadastrar.setOnClickListener(){

            Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()

        }

       btncancelar.setOnClickListener(){

       }

    }
    fun signup (view: View){

        val uuid = UUID.randomUUID()
        val imageName = "images/$uuid.jpg"

        val storageReference = mStorageRef!!.child(imageName)

        var uploadTask = storageReference.putFile(selected!!)

        val uuidString = uuid.toString()

        mAuth!!.createUserWithEmailAndPassword(eTLogin.text.toString(),eTSenha.text.toString())

                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        //add outros campos
                        dbRef!!.child("FoodTruck").child(uuidString).child("nomeEstabelecimento").setValue(edtNomeEstab.text.toString())
                        Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()

                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, R.string.MensagemErro, Toast.LENGTH_SHORT).show()
                }
    }
}
