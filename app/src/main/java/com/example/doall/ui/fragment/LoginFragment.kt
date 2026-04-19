package com.example.doall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.doall.R
import com.example.doall.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    // Buenas prácticas de ViewBinding en Fragmentos
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Lógica del botón de Iniciar Sesión
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmailLogin.text.toString().trim()
            val password = binding.editPassLogin.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Cambia R.id.action_loginFragment_to_mainFragment por la acción real de tu nav_graph
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        } else {
                            Toast.makeText(context, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegación a la pantalla de Registro
        binding.txtIrRegistro.setOnClickListener {
            // Asegúrate de tener esta acción (action_loginFragment_to_registerFragment) en tu nav_graph.xml
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    // Liberar el binding cuando se destruye la vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}