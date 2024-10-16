package com.example.tienda.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tienda.ViewModel.CarritoViewModel
import com.example.tienda.ViewModel.ProductosViewModel


@Composable
fun DetalleProductoView(
    navController: NavController,
    productoId: Int,
    productosViewModel: ProductosViewModel,
    carritoViewModel: CarritoViewModel
) {
    val producto = productosViewModel.obtenerProductoPorId(productoId)


    producto?.let {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE6E1F0)
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = it.imagen),
                    contentDescription = it.nombre,
                    modifier = Modifier
                        .size(350.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = it.nombre,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Precio: $${it.precio}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        color = Color(0xFF424242)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Tallas disponibles: 38, 39, 40",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Colores disponibles: Rojo, Azul, Negro",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = { navController.navigate("listaProductos") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Regresar a la tienda")
                }

                Spacer(modifier = Modifier.padding(8.dp))


                Button(
                    onClick = {
                        carritoViewModel.agregarAlCarrito(it)
                        navController.navigate("listaProductos")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(text = "Agregar al carrito")
                }

                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}