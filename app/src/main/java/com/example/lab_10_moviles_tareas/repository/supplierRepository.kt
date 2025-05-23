package com.example.lab_10_moviles_tareas.repository

import com.example.lab_10_moviles_tareas.model.Supplier
import com.example.lab_10_moviles_tareas.service.tiendaApiService

class supplierRepository(
    private val apiService: tiendaApiService
) {
    suspend fun getSuppliers() = apiService.getSuppliers()
    suspend fun getSupplier(id: Int) = apiService.getSupplier(id)
    suspend fun createSupplier(supplier: Supplier) = apiService.createSupplier(supplier)
    suspend fun updateSupplier(id: Int, supplier: Supplier) = apiService.updateSupplier(id, supplier)
    suspend fun deleteSupplier(id: Int) = apiService.deleteSupplier(id)
}