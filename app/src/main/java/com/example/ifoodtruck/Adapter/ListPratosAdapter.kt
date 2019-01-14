package com.example.ifoodtruck.Adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ifoodtruck.Beans.FoodTruck
import com.example.ifoodtruck.Beans.Prato
import com.example.ifoodtruck.R
import com.example.ifoodtruck.R.id.inputImage
import com.example.ifoodtruck.TelaUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_item_row_foodtruck.view.*
import kotlinx.android.synthetic.main.recycleview_pratos.view.*

class ListPratosAdapter (private val lprato: List<Prato>, private val context: Context): RecyclerView.Adapter<ListPratosAdapter.ViewHolder>()  {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prato = lprato[position]
        holder?.let {
            it.bindView(prato)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycleview_pratos, parent, false)
        return ViewHolder(view)
    }

    override  fun getItemCount(): Int {
        return lprato.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Prato) {
            val nomeprato = itemView.inputNomePrato
            val Descricao = itemView.inputDescricao
            val preco = itemView.inputPreco
            val imagem = itemView.inputImage
            val user = itemView.loginUser

            nomeprato.text = note.NomePrato
            Descricao.text = note.Descricao
            preco.text = note.Preco.toString()
            user.text = note.userLogin
            Picasso.with(itemView.context).load(note.FotoURI).into(imagem);

        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener(){
//                val Detalhes = Intent(itemView.context, TelaUser::class.java)
//                itemView.context.startActivity(Detalhes)

            }
        }

    }

}
