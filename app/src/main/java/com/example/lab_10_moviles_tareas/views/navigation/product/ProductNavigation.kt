package com.example.lab_10_moviles_tareas.views.navigation.product

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab_10_moviles_tareas.viewModel.ProductViewModel

//@Composable
//fun ProductNavigation(
//    viewModel: ProductViewModel,
//    navController: NavHostController
//) {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = ProductScreens.List.route
//    ) {
//        //Rutas para la lista de productos
//        composable("listProducts") {}
//        composable("addProduct") {}
//        composable("productDetail/{productId}") {}
//        composable("updateProduct/{productId}") {}
//        composable("deleteProduct/{productId}") {}
//}