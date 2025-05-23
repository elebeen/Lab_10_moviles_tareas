package com.example.lab_10_moviles_tareas.viewModel

import com.example.lab_10_moviles_tareas.model.Product
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_10_moviles_tareas.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository

) : ViewModel() {
    // Estado para la lista de categorías
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val categories: StateFlow<List<Product>> = _products.asStateFlow()

    // Estado para el proveedor actual (para detalles/edición)
    private val _currentProduct = MutableStateFlow<Product?>(null)
    val currentProduct: StateFlow<Product?> = _currentProduct.asStateFlow()

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
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val categoriesList = repository.getProducts()
                _products.value = categoriesList
                _successMessage.value = "Categorias cargadas correctamente"
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar categorias: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getProduct(id)
                if (response.isSuccessful) {
                    _currentProduct.value = response.body()
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

    fun createProduct(product: Product) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.createProduct(product)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor creado correctamente"
                    loadProducts() // Recargar la lista después de crear
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

    fun updateProduct(id: Int, product: Product) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.updateProduct(id, product)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor actualizado correctamente"
                    loadProducts() // Recargar la lista después de actualizar
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

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.deleteProduct(id)
                if (response.isSuccessful) {
                    _successMessage.value = "Proveedor eliminado correctamente"
                    loadProducts() // Recargar la lista después de eliminar
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

    fun clearCurrentProduct() {
        _currentProduct.value = null
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}