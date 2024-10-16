package com.example.tienda.Navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tienda.View.CarritoView
import com.example.tienda.View.DetalleProductoView
import com.example.tienda.View.ListaProductosView
import com.example.tienda.ViewModel.CarritoViewModel
import com.example.tienda.ViewModel.ProductosViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val productosViewModel: ProductosViewModel = viewModel()
    val carritoViewModel: CarritoViewModel = viewModel()

    NavHost(navController, startDestination = "listaProductos") {
        composable("listaProductos") {
            ListaProductosView(navController, productosViewModel, carritoViewModel)
        }
        composable("detalleProducto/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toInt() ?: 0
            DetalleProductoView(navController, productoId, productosViewModel, carritoViewModel)
        }
        composable("carrito") {
            CarritoView(navController, carritoViewModel)
        }

    }
}
