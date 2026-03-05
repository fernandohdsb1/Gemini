package com.example.examen_final_fernandohernandez.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.examen_final_fernandohernandez.R
import com.example.examen_final_fernandohernandez.databinding.LoginFragmentBinding
import com.example.examen_final_fernandohernandez.model.Usuario
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginFragment: Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        database = FirebaseDatabase.getInstance("https://fernando-hernandedesantamaria-default-rtdb.europe-west1.firebasedatabase.app/")
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            if (binding.userLogin.text.isNotEmpty()&&binding.passLogin.text.isNotEmpty()){
                auth.createUserWithEmailAndPassword(
                    binding.userLogin.text.toString(),
                    binding.passLogin.text.toString()
                ).addOnCompleteListener{
                    if (it.isSuccessful){
                        database.reference
                            .child("usuarios")
                            .child(auth.currentUser!!.uid)
                            .setValue(
                                Usuario(
                                binding.userLogin.text.toString(),
                                binding.passLogin.text.toString())
                            )
                    }
                }
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }else{
                Snackbar.make(binding.root,"Tiene que rellenar todos los campos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }


}