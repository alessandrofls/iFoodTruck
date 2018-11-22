package com.example.ifoodtruck

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.ifoodtruck.Adapter.FoodTrucksRecyclerAdapter
import com.example.ifoodtruck.Beans.FoodTruck
import kotlinx.android.synthetic.main.activity_lista_food_trucks.*

class ListaFoodTrucks : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_food_trucks)

        val recyclerView =l_recyclerView
        recyclerView.adapter = FoodTrucksRecyclerAdapter(notes(), this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

    }

    private fun notes(): List<FoodTruck> {
        return listOf(
                FoodTruck("Japonese",
                        "Seu Japa",
                        1234567,"rua japy","japonese","123","123","jap"),
                FoodTruck("Italyan",
                        "SR. Italiano",
                        1234567,"rua da italia","comida italiana","123","123","ita"),
                FoodTruck("Hamburguer",
                        "Mrs. Hamb", 1234567,"rua da alegria","hamburgueres","123","123","ham"),

                FoodTruck("Japonese",
                        "Seu Japa",
                        1234567,"rua japy","japonese","123","123","jap"),
                FoodTruck("Italyan",
                        "SR. Italiano",
                        1234567,"rua da italia","comida italiana","123","123","ita"),
                FoodTruck("Hamburguer",
                        "Mrs. Hamb", 1234567,"rua da alegria","hamburgueres","123","123","ham"))
    }
}
