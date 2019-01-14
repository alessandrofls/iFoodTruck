package com.example.ifoodtruck.Adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import com.example.ifoodtruck.Beans.FoodTruck
import com.example.ifoodtruck.R
import com.example.ifoodtruck.R.id.imageAdm
import com.example.ifoodtruck.TelaUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tela_adm.*
import kotlinx.android.synthetic.main.recyclerview_item_row_foodtruck.view.*

class FoodTrucksRecyclerAdapter (private val notes: List<FoodTruck>, private val context: Context): Adapter<FoodTrucksRecyclerAdapter.ViewHolder>()  {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodTruck = notes[position]
        holder?.let {
            it.bindView(foodTruck)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_row_foodtruck, parent, false)
        return ViewHolder(view)
    }

    override  fun getItemCount(): Int {
        return notes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: FoodTruck) {
            val nomeestab = itemView.inputNomeEstabelecimento
            val especialidade = itemView.inputEspecialidade
            val telefone = itemView.inputTelefone
            val endereco = itemView.inputEndereco
            val Image = itemView.itemImage

            nomeestab.text = note.nomeEstabelecimento
            especialidade.text = note.especialidade
            telefone.text = note.telefone.toString()
            endereco.text= note.endereco
            Picasso.with(itemView.context).load(note.foto).into(Image);
        }

        init {
            itemView.setOnClickListener{
                val Detalhes = Intent(itemView.context, TelaUser::class.java)
                Detalhes.putExtra("nomeEstab",itemView.inputNomeEstabelecimento.text.toString() )
                Detalhes.putExtra("Espec",itemView.inputEspecialidade.text.toString() )
                Detalhes.putExtra("telefone",itemView.inputTelefone.text.toString() )
                Detalhes.putExtra("endereco",itemView.inputEndereco.text.toString() )


                itemView.context.startActivity(Detalhes)
            }
        }

    }

}
//class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//    init {
//         view.setOnClickListener{
//            val Detalhes = Intent(view.context, TelaUser::class.java)
//            view.context.startActivity(Detalhes)
//        }
//    }
//
//}