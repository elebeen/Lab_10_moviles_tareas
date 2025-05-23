package com.example.lab_10_moviles_tareas.model

import kotlinx.serialization.Serializable

@Serializable
data class Supplier(
    val id: Int,
    var name: String,
    var contact_info: String
)