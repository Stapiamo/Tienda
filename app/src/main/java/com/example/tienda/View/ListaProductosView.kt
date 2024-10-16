package com.example.tienda.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tienda.Navegacion.NavigationBarSample
import com.example.tienda.ViewModel.CarritoViewModel
import com.example.tienda.ViewModel.ProductosViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ListaProductosView(navController: NavController, productosViewModel: ProductosViewModel, carritoViewModel: CarritoViewModel) {
    val productos = productosViewModel.productos.value ?: emptyList()
    val totalArticulosCarrito = carritoViewModel.carrito.value?.sumOf { it.cantidad } ?: 0

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = Color.Black
                ),
                title = { Text("Tienda Shoes Tap") },
            )
        },
        bottomBar = {
            NavigationBarSample(navController, totalArticulosCarrito)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(2.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(vertical = 8.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(productos) { producto ->
                    ProductoCard(
                        producto = producto,
                        onClick = {
                            navController.navigate("detalleProducto/${producto.id}")
                        }
                    )
                }
            }
        }
    }
}