package com.example.lab_10_moviles_tareas.views.supplier

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel

@Composable
fun SupplierEdit(
    viewModel: SupplierViewModel = viewModel(),
    navController: NavHostController,
    isEditing: Boolean,
    supplierId: Int?,
) {
    LaunchedEffect(isEditing) {
        if (isEditing && supplierId != null) {
            viewModel.loadSupplierForEditing(supplierId)
        }
    }
}