package com.example.doall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doall.adapter.EventAdapter
import com.example.doall.databinding.FragmentMainBinding
import com.example.doall.model.Event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: EventAdapter

    // Referencia a la base de datos
    private val database = FirebaseDatabase.getInstance("https://fernando-hernandedesantamaria-default-rtdb.europe-west1.firebasedatabase.app/")
    private val eventosRef = database.getReference("eventos")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Configurar el RecyclerView
        setupRecyclerView()

        // 2. Activar la escucha en tiempo real de Firebase
        escucharEventosFirebase()
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(emptyList())

        binding.recycleMain.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventAdapter
            setHasFixedSize(true)
        }
    }

    private fun escucharEventosFirebase() {
        // Este listener detecta cualquier cambio en el nodo "eventos" de tu base de datos
        eventosRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val listaActualizada = mutableListOf<Event>()

                // Recorremos los hijos del nodo "eventos"
                for (data in snapshot.children) {
                    val evento = data.getValue(Event::class.java)
                    if (evento != null) {
                        listaActualizada.add(evento)
                    }
                }

                // Actualizamos el adaptador con los datos reales de Firebase
                eventAdapter.updateData(listaActualizada)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores de lectura
                if (isAdded) { // Verificamos que el fragmento siga activo
                    Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Importante limpiar el binding para evitar fugas de memoria
        _binding = null
    }
}