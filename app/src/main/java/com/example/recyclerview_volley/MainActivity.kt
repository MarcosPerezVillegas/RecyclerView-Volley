package com.example.recyclerview_volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.recyclerview_volley.Modelo.Personaje
import com.example.recyclerview_volley.adaptador.PersonajeAdapter

class MainActivity : AppCompatActivity() {
    lateinit var miRecyclerView: RecyclerView
    lateinit var listaPersonajes:ArrayList<Personaje>
    lateinit var adaptador:PersonajeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        listaPersonaje.add(Personaje("Chika Fujiwara","https://imgur.com/WGdU0Yc.png"))
        listaPersonaje.add(Personaje("Kaguya Shinomiya","https://cdn.discordapp.com/attachments/472313197836107780/607384191361089536/RHQrQyY.png"))
        listaPersonaje.add(Personaje("Kanna Kamui","https://cdn.discordapp.com/attachments/872026548692209738/872046024611479612/cKGCgYe.png"))
        listaPersonaje.add(Personaje("Asuka Langley Soryu","https://imgur.com/DEzQHLs.png"))
        listaPersonaje.add(Personaje("Fubuki","https://imgur.com/YnPBBZz.png"))
         */

        listaPersonajes = ArrayList<Personaje>()
        adaptador = PersonajeAdapter(listaPersonajes)

        miRecyclerView = findViewById(R.id.RecyclerVista)
        miRecyclerView.adapter = adaptador
        getPersonajes()
        miRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
        fun getPersonajes(){
            val queue = Volley.newRequestQueue(this)
            val url = "https://rickandmortyapi.com/api/character"

            val objectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { respuesta ->

                    val personajesJson = respuesta.getJSONArray("results")
                    for(indice in 0..personajesJson.length()-1){
                        val personajeIndJson = personajesJson.getJSONObject(indice)
                        val personaje = Personaje(personajeIndJson.getString("name"),personajeIndJson.getString("image"))
                        listaPersonajes.add(personaje)
                    }
                    adaptador.notifyDataSetChanged()
                },
                {
                    Log.e("PersonajesAPI","Error")
                }

            )
            queue.add(objectRequest)
        }
}