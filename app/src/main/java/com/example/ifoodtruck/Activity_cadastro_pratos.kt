package com.example.ifoodtruck

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_cadastro_foodtruck.*
import kotlinx.android.synthetic.main.activity_cadastro_pratos.*
import java.util.*

class Activity_cadastro_pratos : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var dbRef: DatabaseReference? = null
    var mStorageRef: StorageReference? = null
    var selected: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pratos)

        var int = getIntent()


        mAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbRef = firebaseDatabase!!.reference
        mStorageRef = FirebaseStorage.getInstance().reference

        btnCadastrarPrato.setOnClickListener(){
            Toast.makeText(this, R.string.messagemcadastrar, Toast.LENGTH_SHORT).show()
        }
    }

    fun cadastrarPrato (view: View){

        var key = mAuth!!.currentUser!!.uid.toString()

        val uuid = UUID.randomUUID()
        val imageName = "ImagensPratos/$uuid.jpg"

        val storageReference = mStorageRef!!.child(imageName)

        var uploadTask = storageReference.putFile(selected!!)

        val uuidString = uuid.toString()

        uploadTask.addOnSuccessListener { taskSnapshot ->

            dbRef!!.child("FoodTruck").child(key).child("ListaPrato").child(uuidString).child("NomePrato").setValue(eTNomePrato.text.toString())
            dbRef!!.child("FoodTruck").child(key).child("ListaPrato").child(uuidString).child("Preco").setValue(eTPreco.text.toString())
            dbRef!!.child("FoodTruck").child(key).child("ListaPrato").child(uuidString).child("Descricao").setValue(editTextDescricao.text.toString())



            storageReference.getDownloadUrl().addOnSuccessListener { uri ->
                dbRef!!.child("FoodTruck").child(key).child("ListaPrato").child(uuidString).child("FotoURI").setValue(uri.toString())
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



fun selectFoto(view: View) {

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    } else {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, 3)
    }

}



override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

    if (requestCode == 1) {
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 3)
        }
    }

    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    if (requestCode == 3 && resultCode == Activity.RESULT_OK && data != null) {
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
