package com.example.lab_10_moviles_tareas.viewModel

import com.example.lab_10_moviles_tareas.model.Category
import com.example.lab_10_moviles_tareas.repository.CategoryRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository

) : ViewModel() {
    // Estado para la lista de categorías
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    // Estado para el proveedor actual (para detalles/edición)
    private val _currentCategory = MutableStateFlow<Category?>(null)
    val currentCategory: StateFlow<Category?> = _currentCategory.asStateFlow()

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
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val categoriesList = repository.getCategories()
                _categories.value = categoriesList
                _successMessage.value = "Categorias cargadas correctamente"
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar categorias: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCategoryById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getCategory(id)
                if (response.isSuccessful) {
                    _currentCategory.value = response.body()
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

    fun createCategory(category: Category) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.createCategory(category)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor creado correctamente"
                    loadCategories() // Recargar la lista después de crear
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

    fun updateCategory(id: Int, category: Category) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.updateCategory(id, category)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor actualizado correctamente"
                    loadCategories() // Recargar la lista después de actualizar
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

    fun deleteCategory(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.deleteCategory(id)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor eliminado correctamente"
                    loadCategories() // Recargar la lista después de eliminar
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

    fun clearCurrentCategory() {
        _currentCategory.value = null
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}