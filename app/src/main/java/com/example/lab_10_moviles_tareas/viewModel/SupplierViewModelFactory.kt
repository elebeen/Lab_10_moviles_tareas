package com.example.lab_10_moviles_tareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab_10_moviles_tareas.RetrofitInstance
import com.example.lab_10_moviles_tareas.repository.SupplierRepository

class SupplierViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupplierViewModel::class.java)) {
            return SupplierViewModel(SupplierRepository(RetrofitInstance.apiService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}