package com.example.lab_10_moviles_tareas.viewModel.fake.util

import com.example.lab_10_moviles_tareas.model.Supplier
import kotlinx.coroutines.flow.StateFlow

interface ISupplierViewModel {
    val suppliers: StateFlow<List<Supplier>>
    val isLoading: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val successMessage: StateFlow<String?>

    fun loadSuppliers()
    fun getSupplierById(id: Int)
    fun createSupplier(supplier: Supplier)
    fun updateSupplier(id: Int, supplier: Supplier)
    fun deleteSupplier(id: Int)
    fun clearCurrentSupplier()
    fun clearMessages()


    // Agrega aquí los demás métodos que necesites
}