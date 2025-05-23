package com.example.lab_10_moviles_tareas.views.supplier.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lab_10_moviles_tareas.ui.theme.Lab_10_moviles_tareasTheme
import com.example.lab_10_moviles_tareas.viewModel.fake.FakeSupplierViewModel
import com.example.lab_10_moviles_tareas.views.supplier.ListSuppliers
import kotlinx.coroutines.flow.MutableStateFlow

//@Preview(showBackground = true, name = "Lista normal")
//@Composable
//fun ListSuppliersPreview_Normal() {
//    Lab_10_moviles_tareasTheme {
//        ListSuppliers(
//            navController = rememberNavController(),
//            viewModel = FakeSupplierViewModel()
//        )
//    }
//}

@Preview(showBackground = true, name = "Lista vacía")
@Composable
fun ListSuppliersPreview_Empty() {
    Lab_10_moviles_tareasTheme {
        val emptyViewModel = FakeSupplierViewModel().apply {
            (suppliers as MutableStateFlow).value = emptyList()
        }

        ListSuppliers(
            navController = rememberNavController(),
            viewModel = emptyViewModel
        )
    }
}

@Preview(showBackground = true, name = "Cargando")
@Composable
fun ListSuppliersPreview_Loading() {
    Lab_10_moviles_tareasTheme {
        val loadingViewModel = FakeSupplierViewModel().apply {
            (isLoading as MutableStateFlow).value = true
        }

        ListSuppliers(
            navController = rememberNavController(),
            viewModel = loadingViewModel
        )
    }
}

@Preview(showBackground = true, name = "Con error")
@Composable
fun ListSuppliersPreview_Error() {
    Lab_10_moviles_tareasTheme {
        val errorViewModel = FakeSupplierViewModel().apply {
            (errorMessage as MutableStateFlow).value = "Error de conexión con el servidor"
        }

        ListSuppliers(
            navController = rememberNavController(),
            viewModel = errorViewModel
        )
    }
}