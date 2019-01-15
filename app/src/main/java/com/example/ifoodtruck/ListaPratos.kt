package com.example.ifoodtruck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.ifoodtruck.Adapter.FoodTrucksRecyclerAdapter
import com.example.ifoodtruck.Adapter.ListPratosAdapter
import com.example.ifoodtruck.Beans.FoodTruck
import com.example.ifoodtruck.Beans.Prato
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lista_food_trucks.*
import kotlinx.android.synthetic.main.activity_lista_pratos.*

class ListaPratos : AppCompatActivity() {
    var lstprato: List<Prato>? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pratos)

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()

        lstprato = pratos()
        val recyclerView = list_recyclerview
        recyclerView.adapter = ListPratosAdapter(lstprato as ArrayList<Prato>, this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

//    private fun pratos(): List<Prato> {
//        return listOf(
//                Prato("Sushi",
//                        "Seu Japa",
//                        20.0),
//                Prato("Temaki",
//                        "pexe cru com alga",
//                        10.50),
//                Prato("Yakissoba",
//                        "macarrao japones", 22.30),
//                Prato("Sushi",
//                        "Seu Japa",
//                        20.0),
//                Prato("Temaki",
//                        "pexe cru com alga",
//                        10.50),
//                Prato("Yakissoba",
//                        "macarrao japones", 22.30))
//    }

    fun pratos(): List<Prato> {

        var prato: ArrayList<Prato> = ArrayList()

        val newReference = firebaseDatabase!!.getReference("ListaPrato")

        newReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {


                prato.clear()

                for (snapshot in p0.children) {

                    val hashMap = snapshot.value as HashMap<String, String>

                    if (hashMap.size > 0) {

                        val userprato = Prato(hashMap["NomePrato"], hashMap["Descricao"], hashMap["Preco"],
                                hashMap["userLogin"],hashMap["FotoURI"])
                        prato.add(userprato)


                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        return prato

    }
}
