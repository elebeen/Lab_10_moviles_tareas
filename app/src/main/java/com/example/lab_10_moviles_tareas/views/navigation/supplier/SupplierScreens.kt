package com.example.lab_10_moviles_tareas.views.navigation.supplier

sealed class SupplierScreens(
    val route: String,
    val title: String
) {
    object List : SupplierScreens("suppliers_list", "Proveedores")
    object Add : SupplierScreens("add_supplier", "Nuevo Proveedor")
    object Edit : SupplierScreens("edit_supplier/{supplierId}", "Editar Proveedor") {
        fun createRoute(supplierId: Int) = "edit_supplier/$supplierId"
    }
    object Delete : SupplierScreens("deleteSupplier/{supplierId}", "Eliminar Proveedor")
}