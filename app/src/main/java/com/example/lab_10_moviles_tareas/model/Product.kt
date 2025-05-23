package com.example.lab_10_moviles_tareas.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    var name: String,
    var description: String,
    var price: Double,
    var stock: Int,
    var category: Category,
    var suplier: Supplier,
    val created_at: String,
    var updated_at: String
)
