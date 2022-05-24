package com.example.recyclerview_volley.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_volley.Modelo.Personaje
import com.example.recyclerview_volley.R
import com.squareup.picasso.Picasso

class PersonajeAdapter(val listaPersonajes: ArrayList<Personaje>): RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vista,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tNombre.text = listaPersonajes[position].nombre
        Picasso.get().load(listaPersonajes[position].foto).into(holder.iFoto)
    }

    override fun getItemCount(): Int {
        return  listaPersonajes.size
    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        val tNombre: TextView
        val iFoto: ImageView

        init {
            tNombre = vista.findViewById(R.id.tNombre)
            iFoto = vista.findViewById(R.id.imgAvatar)
        }
    }

}