package com.example.lab_10_moviles_tareas.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab_10_moviles_tareas.repository.SupplierRepository
import com.example.lab_10_moviles_tareas.service.ShopApiService
import com.example.lab_10_moviles_tareas.ui.theme.Lab_10_moviles_tareasTheme
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun MainScreen() {
    val url = "http://192.168.0.106:8000/"
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(ShopApiService::class.java)

    val viewModel: SupplierViewModel = viewModel(
        factory = SupplierViewModelFactory(
            SupplierRepository(service)
        )
    )

    // 3. Configuración del tema y navegación
    Lab_10_moviles_tareasTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SupplierNavigation(viewModel = viewModel)
        }
    }
}