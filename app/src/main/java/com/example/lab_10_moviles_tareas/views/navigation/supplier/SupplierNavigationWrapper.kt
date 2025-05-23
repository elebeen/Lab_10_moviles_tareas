package com.example.lab_10_moviles_tareas.views.navigation.supplier

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel

@Composable
fun SupplierNavigationWrapper(
    parentNavController: NavHostController,
    viewModel: SupplierViewModel
) {
    val supplierNavController = rememberNavController()

    // Manejar la navegación de retorno al gráfico principal
    BackHandler(enabled = supplierNavController.previousBackStackEntry != null) {
        supplierNavController.popBackStack()
    }

    SupplierNavigation(
        viewModel = viewModel,
        navController = supplierNavController
    )
}