package com.example.lab_10_moviles_tareas.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel
import com.example.lab_10_moviles_tareas.views.supplier.ListSuppliers

@Composable
fun SupplierNavigation(
    viewModel: SupplierViewModel,
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "listSuppliers"
    ) {

        // Ruta para la lista de proveedores
        composable("listSuppliers") {
            ListSuppliers(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("addSupplier") {}
        composable("supplierDetail/{supplierId}") { backStackEntry -> }
        composable("updateSupplier/{supplierId}") { backStackEntry -> }
        composable("deleteSupplier/{supplierId}") { backStackEntry -> }

        //Rutas para la lista de productos
        composable("listProducts") {}
        composable("addProduct") {}
        composable("productDetail/{productId}") {}
        composable("updateProduct/{productId}") {}
        composable("deleteProduct/{productId}") {}

        //Rutas para las listas de categorias
        composable("listCategories") {}
        composable("addCategory") {}
        composable("categoryDetail/{categoryId}") {}
        composable("updateCategory/{categoryId}") {}
        composable("deleteCategory/{categoryId}") {}
    }
}