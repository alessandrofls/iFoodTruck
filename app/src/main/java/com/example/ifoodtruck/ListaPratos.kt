package com.example.ifoodtruck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.ifoodtruck.Adapter.FoodTrucksRecyclerAdapter
import com.example.ifoodtruck.Adapter.ListPratosAdapter
import com.example.ifoodtruck.Beans.FoodTruck
import com.example.ifoodtruck.Beans.Prato
import kotlinx.android.synthetic.main.activity_lista_food_trucks.*
import kotlinx.android.synthetic.main.activity_lista_pratos.*

class ListaPratos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pratos)

        val recyclerView = list_recyclerview
        recyclerView.adapter = ListPratosAdapter(pratos(), this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun pratos(): List<Prato> {
        return listOf(
                Prato("Sushi",
                        "Seu Japa",
                        20.0),
                Prato("Temaki",
                        "pexe cru com alga",
                        10.50),
                Prato("Yakissoba",
                        "macarrao japones", 22.30),
                Prato("Sushi",
                        "Seu Japa",
                        20.0),
                Prato("Temaki",
                        "pexe cru com alga",
                        10.50),
                Prato("Yakissoba",
                        "macarrao japones", 22.30))
    }
}
