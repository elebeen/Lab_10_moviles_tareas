package com.example.lab_10_moviles_tareas.repository

import com.example.lab_10_moviles_tareas.model.Product
import com.example.lab_10_moviles_tareas.service.tiendaApiService

class productRepository(
    private val apiService: tiendaApiService
) {
    suspend fun getProducts() = apiService.getProducts()
    suspend fun getProduct(id: Int) = apiService.getProduct(id)
    suspend fun createProduct(product: Product) = apiService.createProduct(product)
    suspend fun updateProduct(id: Int, product: Product) = apiService.updateProduct(id, product)
    suspend fun deleteProduct(id: Int) = apiService.deleteProduct(id)
}