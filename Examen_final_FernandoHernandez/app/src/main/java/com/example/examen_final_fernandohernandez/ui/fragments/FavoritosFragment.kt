package com.example.examen_final_fernandohernandez.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen_final_fernandohernandez.adapter.AdapterCoche
import com.example.examen_final_fernandohernandez.databinding.FavoritosFragmentBinding
import com.example.examen_final_fernandohernandez.databinding.LoginFragmentBinding
import com.example.examen_final_fernandohernandez.dataset.DataSet
import com.example.examen_final_fernandohernandez.model.Coche

class FavoritosFragment: Fragment() {
    private lateinit var binding: FavoritosFragmentBinding
    private lateinit var listaFavs: ArrayList<Coche>
    private lateinit var adapterCoche: AdapterCoche

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listaFavs= DataSet.listaFavoritos
        adapterCoche = AdapterCoche(context,listaFavs)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritosFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleFavoritos.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )
        binding.recycleFavoritos.adapter = adapterCoche
    }

    override fun onResume() {
        super.onResume()
    }
}