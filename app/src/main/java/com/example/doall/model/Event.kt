package com.example.doall.model

// El "data class" es una clase especial de Kotlin solo para guardar datos
data class Event(
    val id: String,                 // El identificador único de Firebase
    val name: String,               // Ej: "Pacha Madrid"
    val location: String,           // Ej: "Calle Falsa 123"
    val friendsAttending: Int,      // Ej: 5 (Para tu función de ver amigos)
    val imageUrl: String = ""       // La URL de la foto del cartel o la discoteca
) {
    // Es obligatorio tener un constructor vacío si vas a leer datos de
    // Firebase Realtime Database directamente a esta clase
    constructor() : this("", "", "", 0, "")
}