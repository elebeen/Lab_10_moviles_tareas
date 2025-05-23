package com.example.lab_10_moviles_tareas.views.supplier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lab_10_moviles_tareas.model.Supplier
import com.example.lab_10_moviles_tareas.viewModel.SupplierViewModel
import com.example.lab_10_moviles_tareas.viewModel.fake.FakeSupplierViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListSuppliers(
    navController: NavHostController,
//    viewModel: SupplierViewModel = viewModel()
    viewModel: FakeSupplierViewModel = viewModel()
) {
    val suppliers by viewModel.suppliers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSuppliers()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Proveedores") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("addSupplier")
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addSupplier")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                CenterLoadingIndicator()
            } else {
                if (suppliers.isEmpty()) {
                    EmptyListMessage()
                } else {
                    SuppliersListContent(
                        suppliers = suppliers,
                        onItemClick = { supplier ->
                            navController.navigate("supplierDetail/${supplier.id}")
                        },
                        onDeleteClick = { supplier ->
                            viewModel.deleteSupplier(supplier.id)
                        }
                    )
                }
            }

            // Mostrar mensajes de error/éxito
            errorMessage?.let { message ->
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    action = {
                        Button(onClick = { viewModel.clearMessages() }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(message)
                }
            }

            successMessage?.let { message ->
                Snackbar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    action = {
                        Button(onClick = { viewModel.clearMessages() }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(message)
                }
            }
        }
    }
}

@Composable
private fun CenterLoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyListMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No hay proveedores registrados", style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun SuppliersListContent(
    suppliers: List<Supplier>,
    onItemClick: (Supplier) -> Unit,
    onDeleteClick: (Supplier) -> Unit
) {
    LazyColumn {
        items(suppliers) { supplier ->
            SupplierListItem(
                supplier = supplier,
                onItemClick = onItemClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun SupplierListItem(
    supplier: Supplier,
    onItemClick: (Supplier) -> Unit,
    onDeleteClick: (Supplier) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(supplier) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = supplier.name,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = supplier.contact_info,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            IconButton(
                onClick = { onDeleteClick(supplier) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
            }
        }
    }
}