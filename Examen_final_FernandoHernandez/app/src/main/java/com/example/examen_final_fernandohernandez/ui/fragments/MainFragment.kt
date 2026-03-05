package com.example.examen_final_fernandohernandez.ui.fragments

import com.example.examen_final_fernandohernandez.databinding.MainFragmentBinding
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen_final_fernandohernandez.R
import com.example.examen_final_fernandohernandez.adapter.AdapterCoche
import com.example.examen_final_fernandohernandez.model.Coche


class MainFragment: Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var listaCoches: ArrayList<Coche>
    private lateinit var adapterCoche: AdapterCoche

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listaCoches = ArrayList()
        for (i in 0..9){
            listaCoches.add(Coche("Coche ${i+1}", "muy chulo", " https://images2.imgbox.com/94/f2/NN6Ph45r_o.png"))
        }
        adapterCoche = AdapterCoche(context,listaCoches)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleMain.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )
        binding.recycleMain.adapter = adapterCoche

        binding.bontonfavoritoooooooooos.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment) }
    }

    override fun onResume() {
        super.onResume()
    }


}
