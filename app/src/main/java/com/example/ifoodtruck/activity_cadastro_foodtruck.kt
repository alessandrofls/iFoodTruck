package com.example.ifoodtruck

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_cadastro_foodtruck.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import android.Manifest
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_adm.*

class activity_cadastro_foodtruck : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null
    var mStorageRef: StorageReference? = null
    var selected: Uri? = null
    var tipo : String? = null
    var chave : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_foodtruck)

        var int = getIntent()


         tipo = int.getStringExtra("tipo")

        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase!!.reference
        mStorageRef = FirebaseStorage.getInstance().reference

//        btncadastrar.setOnClickListener(){
//
//            Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()
//
//        }

//       btncancelar.setOnClickListener(){
//
//       }



    }
    fun signup (view: View) {

        if (tipo.equals("cadastrar")) {


            val uuid = UUID.randomUUID()
            val imageName = "ImagensFoodTrucks/$uuid.jpg"

            val storageReference = mStorageRef!!.child(imageName)

            var uploadTask = storageReference.putFile(selected!!)

            val uuidString = uuid.toString()


            if (eTSenha.text.toString().equals(eTConfirmaSenha.text.toString()) && cbAccept.isChecked) {

                mAuth!!.createUserWithEmailAndPassword(eTLogin.text.toString(), eTSenha.text.toString())

                uploadTask.addOnSuccessListener { taskSnapshot ->

                    dbRef!!.child("FoodTruck").child(uuidString).child("Id").setValue(uuidString)
                    dbRef!!.child("FoodTruck").child(uuidString).child("nomeEstabelecimento").setValue(edtNomeEstab.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("NomeProprietario").setValue(eTNomeProprietario.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("telefone").setValue(eTTelefone.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("endereco").setValue(eTcadEndereco.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("especialidade").setValue(eTEspecialidade.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("login").setValue(eTLogin.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("senha").setValue(eTSenha.text.toString())


                    storageReference.getDownloadUrl().addOnSuccessListener { uri ->
                        dbRef!!.child("FoodTruck").child(uuidString).child("foto").setValue(uri.toString())
                    }
                }

                uploadTask.addOnCompleteListener { task ->
                    if (task.isComplete) {
                        Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()

                    }
                }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, R.string.MensagemErro, Toast.LENGTH_SHORT).show()
                        }

            }

        } else if (tipo.equals("update")) {


            var serAtual = mAuth!!.currentUser!!.email.toString()

//        val query = firebaseDatabase!!.getReference("FoodTruck")
            val raiz = FirebaseDatabase.getInstance().reference
            val query = raiz.child("FoodTruck").orderByChild("login").equalTo(serAtual)


            query.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot) {

                    println(p0)
                    println("children: " + p0!!.children)
                    println("key:" + p0!!.key)
                    println("value:" + p0!!.value)

                    println("ref:" + p0!!.ref)

//               raiz.child().child(p0.key).

                    for (snapshot in p0.children) {

                        val hashMap = snapshot.value as HashMap<String, String>
                        println("valueeee" + snapshot.value)

                        if (hashMap.size > 0) {
                            chave = hashMap["Id"]


                        }
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })


            val uuid = chave
            val imageName = "ImagensFoodTrucks/$uuid.jpg"

            val storageReference = mStorageRef!!.child(imageName)

            var uploadTask = storageReference.putFile(selected!!)

            val uuidString = uuid.toString()


            if (eTSenha.text.toString().equals(eTConfirmaSenha.text.toString()) && cbAccept.isChecked) {

                mAuth!!.createUserWithEmailAndPassword(eTLogin.text.toString(), eTSenha.text.toString())

                uploadTask.addOnSuccessListener { taskSnapshot ->

                    dbRef!!.child("FoodTruck").child(uuidString).child("nomeEstabelecimento").setValue(edtNomeEstab.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("NomeProprietario").setValue(eTNomeProprietario.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("telefone").setValue(eTTelefone.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("endereco").setValue(eTcadEndereco.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("especialidade").setValue(eTEspecialidade.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("login").setValue(eTLogin.text.toString())
                    dbRef!!.child("FoodTruck").child(uuidString).child("senha").setValue(eTSenha.text.toString())


                    storageReference.getDownloadUrl().addOnSuccessListener { uri ->
                        dbRef!!.child("FoodTruck").child(uuidString).child("foto").setValue(uri.toString())
                    }
                }

                uploadTask.addOnCompleteListener { task ->
                    if (task.isComplete) {
                        Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()

                    }
                }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, R.string.MensagemErro, Toast.LENGTH_SHORT).show()
                        }


            }
        }

    }




    fun selectImage(view: View) {

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 2)
        }

    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selected = data.data

            try {

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selected)
                imageView.setImageBitmap(bitmap)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}
