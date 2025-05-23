package com.example.lab_10_moviles_tareas.views.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.lab_10_moviles_tareas.repository.SupplierRepository
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModelFactory
import com.example.lab_10_moviles_tareas.views.navigation.supplier.SupplierNavigationWrapper
import com.example.lab_10_moviles_tareas.views.navigation.supplier.SupplierScreens

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    // Crea el ViewModel con la fábrica
    val supplierViewModel: SupplierViewModel = viewModel(
        factory = SupplierViewModelFactory()
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "suppliers_graph",
            modifier = Modifier.padding(padding)
        ) {

            // Gráfico de Productos
//            navigation(
//                route = "products_graph",
//                startDestination = "products_list"
//            ) {
//                composable("products_list") { ProductsListScreen(navController) }
//                composable("product_detail/{id}") { /* ... */ }
//                composable("edit_product/{id}") { /* ... */ }
//            }

            // Gráfico de Proveedores
            navigation(
                route = "suppliers_graph",
                startDestination = SupplierScreens.List.route
            ) {
                composable(SupplierScreens.List.route) {
                    SupplierNavigationWrapper(
                        parentNavController = navController,
                        viewModel = supplierViewModel // Pasamos el ViewModel creado
                    )
                }
            }

            // Gráfico de Categorías
//            navigation(
//                route = "categories_graph",
//                startDestination = "categories_list"
//            ) {
//                composable("categories_list") { CategoriesListScreen(navController) }
//                composable("category_detail/{id}") { /* ... */ }
//                composable("edit_category/{id}") { /* ... */ }
//            }
        }
    }
}