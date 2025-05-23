package com.example.lab_10_moviles_tareas.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    var name: String,
    var description: String
)
