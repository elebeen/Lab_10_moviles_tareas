package com.example.lab_10_moviles_tareas.repository

import com.example.lab_10_moviles_tareas.model.Category
import com.example.lab_10_moviles_tareas.service.tiendaApiService

class categoryRepository(
    private val apiService: tiendaApiService
) {
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getCategory(id: Int) = apiService.getCategory(id)
    suspend fun createCategory(category: Category) = apiService.createCategory(category)
    suspend fun updateCategory(id: Int, category: Category) = apiService.updateCategory(id, category)
    suspend fun deleteCategory(id: Int) = apiService.deleteCategory(id)
}