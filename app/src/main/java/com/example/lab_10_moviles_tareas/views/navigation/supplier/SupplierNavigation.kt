package com.example.lab_10_moviles_tareas.views.navigation.supplier

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel
import com.example.lab_10_moviles_tareas.views.supplier.ListSuppliers
import com.example.lab_10_moviles_tareas.views.supplier.SupplierDelete
import com.example.lab_10_moviles_tareas.views.supplier.SupplierEdit

@Composable
fun SupplierNavigation(
    viewModel: SupplierViewModel,
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SupplierScreens.List.route
    ) {

        // Ruta para la lista de proveedores
        composable(route = SupplierScreens.List.route) {
            ListSuppliers(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = SupplierScreens.Edit.route,
            arguments = listOf(navArgument("supplierId") { type = NavType.IntType })
        ) { backStackEntry ->
            val supplierId = backStackEntry.arguments?.getInt("supplierId")
            SupplierEdit(
                viewModel = viewModel,
                navController = navController,
                isEditing = true,
                supplierId = supplierId
            )
        }
        composable(route = SupplierScreens.Add.route) {
            SupplierEdit(
                viewModel = viewModel,
                navController = navController,
                isEditing = false,
                supplierId = null
            )
        }
        composable(
            route = SupplierScreens.Delete.route,
            arguments = listOf(navArgument("supplierId") { type = NavType.IntType })
        ) { backStackEntry ->
            val supplierId = backStackEntry.arguments?.getInt("supplierId")
            SupplierDelete(
                viewModel = viewModel,
                navController = navController,
                supplierId = supplierId ?: -1
            )
        }



        //Rutas para las listas de categorias
        composable("listCategories") {}
        composable("addCategory") {}
        composable("updateCategory/{categoryId}") {}
        composable("deleteCategory/{categoryId}") {}
    }
}