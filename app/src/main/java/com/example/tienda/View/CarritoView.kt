package com.example.tienda.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tienda.Model.CarritoItem
import com.example.tienda.ViewModel.CarritoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoView(navController: NavController, carritoViewModel: CarritoViewModel) {

    val productosEnCarrito by carritoViewModel.carrito.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Carrito de compras") },
            actions = {
                IconButton(onClick = {
                    carritoViewModel.limpiarCarrito()
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Limpiar carrito")
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(productosEnCarrito) { carritoItem ->
                    ProductoCarritoItem(
                        carritoItem = carritoItem, carritoViewModel = carritoViewModel
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                val total = productosEnCarrito.sumOf { it.producto.precio * it.cantidad }

                Text(
                    text = "Total: $${total}",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF424242)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("l") }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Finalizar Compra")
                }
            }
        }
    }
}

@Composable
fun ProductoCarritoItem(carritoItem: CarritoItem, carritoViewModel: CarritoViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = carritoItem.producto.nombre,
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = "$${carritoItem.producto.precio}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF424242))
                )
            }

            Column {
                Row {
                    Button(
                        onClick = {
                            if (carritoItem.cantidad > 1) {
                                carritoViewModel.actualizarCantidad(
                                    carritoItem.producto, carritoItem.cantidad - 1
                                )
                            } else {
                                carritoViewModel.removerDelCarrito(carritoItem.producto)
                            }
                        }
                    ) {
                        Text(text = "-")
                    }

                    Text(
                        text = "${carritoItem.cantidad}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF424242))
                    )

                    Button(
                        onClick = {
                            carritoViewModel.actualizarCantidad(
                                carritoItem.producto, carritoItem.cantidad + 1
                            )
                        }
                    ) {
                        Text(text = "+")
                    }
                }

            }
        }
    }
}

