package com.example.lab_10_moviles_tareas.viewModel.fake

import com.example.lab_10_moviles_tareas.model.Supplier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeSupplierViewModel {
    val suppliers: StateFlow<List<Supplier>> = MutableStateFlow(
        listOf(
            Supplier(1, "Proveedor Uno", "contacto@uno.com"),
            Supplier(2, "Proveedor Dos", "contacto@dos.com"),
            Supplier(3, "Proveedor Tres", "contacto@tres.com"),
            Supplier(4, "Proveedor Cuatro", "contacto@cuatro.com"),
            Supplier(5, "Proveedor Cinco", "contacto@cinco.com")
        )
    ).asStateFlow()

    val isLoading: StateFlow<Boolean> = MutableStateFlow(false).asStateFlow()
    val errorMessage: StateFlow<String?> = MutableStateFlow(null).asStateFlow()
    val successMessage: StateFlow<String?> = MutableStateFlow(null).asStateFlow()

    fun loadSuppliers() {
        // No hace nada en el fake
    }

    fun deleteSupplier(id: Int) {
        // Simula la eliminación
        (suppliers as MutableStateFlow).value = suppliers.value.filter { it.id != id }
        (successMessage as MutableStateFlow).value = "Proveedor eliminado"
    }

    fun clearMessages() {
        (errorMessage as MutableStateFlow).value = null
        (successMessage as MutableStateFlow).value = null
    }
}