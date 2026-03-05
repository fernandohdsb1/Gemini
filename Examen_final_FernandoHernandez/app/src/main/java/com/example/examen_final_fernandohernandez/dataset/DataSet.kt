package com.example.examen_final_fernandohernandez.dataset

import com.example.examen_final_fernandohernandez.model.Coche

class DataSet {
    companion object{
        val listaFavoritos = ArrayList<Coche>()
        fun aniadirFavorito(coche: Coche){
            listaFavoritos.add(coche)
        }
    }
}