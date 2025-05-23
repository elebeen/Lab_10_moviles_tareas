package com.example.lab_10_moviles_tareas.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_10_moviles_tareas.model.Supplier
import com.example.lab_10_moviles_tareas.repository.supplierRepository
import kotlinx.coroutines.launch

class SupplierViewModel(
    private val repository: supplierRepository
) : ViewModel() {
    private var _supplierList = mutableStateListOf<Supplier>()
    var supplierList: List<Supplier> = _supplierList

    init {
        getSuppliers()
    }

    fun getSuppliers() {
        viewModelScope.launch {
            _supplierList = repository.getSuppliers()
        }
    }

    fun getSupplier(id: Int) {
        viewModelScope.launch {
            _supplierList = repository.getSupplier(id)
        }
    }

    fun createSupplier(supplier: Supplier) {
        viewModelScope.launch {
            repository.createSupplier(supplier)
        }
    }

    fun updateSupplier(id: Int, supplier: Supplier) {
        viewModelScope.launch {
            repository.updateSupplier(id, supplier)
        }
    }

    fun deleteSupplier(id: Int) {
        viewModelScope.launch {
            repository.deleteSupplier(id)
        }
    }
}