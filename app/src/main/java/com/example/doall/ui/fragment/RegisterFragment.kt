package com.example.doall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.doall.R
import com.example.doall.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        // Instancia de tu Realtime Database
        database = FirebaseDatabase.getInstance("https://fernando-hernandedesantamaria-default-rtdb.europe-west1.firebasedatabase.app/")

        binding.btnRegistrarUsuario.setOnClickListener {
            val nombre = binding.editNombreRegistro.text.toString().trim()
            val email = binding.editEmailRegistro.text.toString().trim()
            val password = binding.editPassRegistro.text.toString().trim()
            val confirmPassword = binding.editPassConfirmRegistro.text.toString().trim()
            val aceptaTerminos = binding.cbTerminos.isChecked

            // 1. Validar campos vacíos
            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Validar que las contraseñas coinciden
            if (password != confirmPassword) {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Validar términos y condiciones
            if (!aceptaTerminos) {
                Toast.makeText(context, "Debes aceptar los Términos y Condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. Crear el usuario en Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid ?: ""

                        // 5. Guardar datos adicionales en Realtime Database
                        val userMap = mapOf(
                            "nombre" to nombre,
                            "email" to email,
                            "rol" to "usuario" // Útil para el futuro si añades rol "discoteca"
                        )

                        database.reference.child("usuarios").child(uid).setValue(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                    } else {
                        Toast.makeText(context, "Error al registrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Navegación de vuelta al Login
        binding.txtIrLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}