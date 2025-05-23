package com.example.lab_10_moviles_tareas.views.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

// app/src/main/java/com/example/tuapp/ui/navigation/BottomNavItem.kt
sealed class BottomNavItem(
    val graphRoute: String,
    val title: String,
    val icon: ImageVector
) {
    object Products : BottomNavItem(
        graphRoute = "products",
        title = "Productos",
        icon = Icons.Default.ShoppingCart
    )

    object Suppliers : BottomNavItem(
        graphRoute = "suppliers",
        title = "Proveedores",
        icon = Icons.Default.People
    )

    object Categories : BottomNavItem(
        graphRoute = "categories",
        title = "Categorías",
        icon = Icons.Default.Category
    )

    companion object {
        val items = listOf(Products, Suppliers, Categories)
    }
}