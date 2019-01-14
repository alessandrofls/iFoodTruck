package com.example.ifoodtruck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.ifoodtruck.Adapter.FoodTrucksRecyclerAdapter
import com.example.ifoodtruck.Beans.FoodTruck
import com.example.ifoodtruck.Beans.Prato
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_lista_food_trucks.*

class ListaFoodTrucks : AppCompatActivity() {
    var lstFood: List<FoodTruck>? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    var firebaseDatabase: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_food_trucks)

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()


        lstFood = CarregaDados()
        val recyclerView = l_recyclerView
        recyclerView.adapter = FoodTrucksRecyclerAdapter(lstFood as ArrayList<FoodTruck>, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

    }

//    private fun notes(): List<FoodTruck> {
//        return listOf(
//                FoodTruck("Japonese",
//                        "Seu Japa",
//                        "1234567","rua japy","japonese","123","123","jap",""),
//                FoodTruck("Italyan",
//                        "SR. Italiano",
//                        "1234567","rua da italia","comida italiana","123","123","ita",""),
//                FoodTruck("Hamburguer",
//                        "Mrs. Hamb", "1234567","rua da alegria","hamburgueres","123","123","ham",""),
//
//                FoodTruck("Japonese",
//                        "Seu Japa",
//                        "1234567","rua japy","japonese","123","123","jap",""),
//                FoodTruck("Italyan",
//                        "SR. Italiano",
//                        "1234567","rua da italia","comida italiana","123","123","ita",""),
//                FoodTruck("Hamburguer",
//                        "Mrs. Hamb", "1234567","rua da alegria","hamburgueres","123","123","ham",""))
//    }
//}

     fun CarregaDados(): ArrayList<FoodTruck> {
         var foodtruck: ArrayList<FoodTruck> = ArrayList()

    val newReference = firebaseDatabase!!.getReference("FoodTruck")

    newReference.addValueEventListener(object : ValueEventListener {

        override fun onDataChange(p0: DataSnapshot) {

            foodtruck.clear()

//            println(p0)
//            println("children: " + p0!!.children)
//            println("key:" + p0!!.key)
//            println("value:" + p0!!.value)



            for (snapshot in p0.children) {

                val hashMap = snapshot.value as HashMap<String, String>
                println("HashMap Size: " +hashMap.size)

                if (hashMap.size > 0) {

                    val UserfoodTruck = FoodTruck(hashMap["nomeEstabelecimento"], hashMap["NomeProprietario"], hashMap["telefone"],
                            hashMap["endereco"],hashMap["especialidade"], hashMap["senha"] ,hashMap["confirmacaoSenha"],
                            hashMap["login"],hashMap["foto"] )
                    foodtruck.add(UserfoodTruck)


                }
            }
        }


        override fun onCancelled(p0: DatabaseError) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    })
        return foodtruck
}
}




