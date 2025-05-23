package com.example.lab_10_moviles_tareas.viewModel

import com.example.lab_10_moviles_tareas.model.Supplier
import com.example.lab_10_moviles_tareas.repository.SupplierRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SupplierViewModel(
    private val repository: SupplierRepository
) : ViewModel() {

    // Estado para la lista de proveedores
    private val _suppliers = MutableStateFlow<List<Supplier>>(emptyList())
    val suppliers: StateFlow<List<Supplier>> = _suppliers.asStateFlow()

    // Estado para el proveedor actual (para detalles/edición)
    private val _currentSupplier = MutableStateFlow<Supplier?>(null)
    val currentSupplier: StateFlow<Supplier?> = _currentSupplier.asStateFlow()

    // Estado para manejar errores
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // Estado para operaciones exitosas
    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    // Estado de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadSuppliers()
    }

    fun loadSuppliers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val suppliersList = repository.getSuppliers()
                _suppliers.value = suppliersList
                _successMessage.value = "Proveedores cargados correctamente"
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar proveedores: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getSupplierById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getSupplier(id)
                if (response.isSuccessful) {
                    _currentSupplier.value = response.body()
                } else {
                    _errorMessage.value = "Error al obtener proveedor: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener proveedor: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createSupplier(supplier: Supplier) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.createSupplier(supplier)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor creado correctamente"
                    loadSuppliers() // Recargar la lista después de crear
                } else {
                    _errorMessage.value = "Error al crear proveedor: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al crear proveedor: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSupplier(id: Int, supplier: Supplier) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.updateSupplier(id, supplier)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor actualizado correctamente"
                    loadSuppliers() // Recargar la lista después de actualizar
                } else {
                    _errorMessage.value = "Error al actualizar proveedor: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al actualizar proveedor: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteSupplier(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.deleteSupplier(id)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor eliminado correctamente"
                    loadSuppliers() // Recargar la lista después de eliminar
                } else {
                    _errorMessage.value = "Error al eliminar proveedor: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al eliminar proveedor: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearCurrentSupplier() {
        _currentSupplier.value = null
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}