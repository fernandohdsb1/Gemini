package com.example.examen_final_fernandohernandez.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examen_final_fernandohernandez.R
import com.example.examen_final_fernandohernandez.dataset.DataSet
import com.example.examen_final_fernandohernandez.model.Coche


class AdapterCoche(var contexto: Context, var listaDatos: List<*>) :
    RecyclerView.Adapter<AdapterCoche.MyHolder>() {



    inner class MyHolder(var view: View) : RecyclerView.ViewHolder(view) {

        lateinit var nombreCoche: TextView
        lateinit var bontonFav: Button
        var imagen: ImageView


        init {
            nombreCoche = view.findViewById(R.id.nombreCoche)
            imagen = view.findViewById(R.id.imagenRecycler)
            bontonFav = view.findViewById(R.id.btnFav)

        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        var view: View =
            LayoutInflater.from(contexto).inflate(R.layout.adapter_coche, parent, false)

        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var coche: Coche = listaDatos[position] as Coche
        Glide.with(contexto).load(coche.img).placeholder(R.drawable.ic_launcher_background).into(holder.imagen)
        holder.nombreCoche.setText(coche.nombre)
        holder.bontonFav.setOnClickListener {
            DataSet.aniadirFavorito(coche)
        }
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun mostrarRecycle(){
        notifyDataSetChanged()
    }
}
