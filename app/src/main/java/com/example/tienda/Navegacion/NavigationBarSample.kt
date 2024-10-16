package com.example.tienda.Navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationBarSample(navController: NavController, totalArticulosCarrito: Int) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Brujula", "Carrito", "Notificaciones", "Cuenta")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.LocationOn,
        Icons.Filled.ShoppingCart,
        Icons.Filled.Notifications,
        Icons.Filled.Person
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (item == "Carrito" && totalArticulosCarrito > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = Color.Red
                                ) {
                                    Text(
                                        text = "$totalArticulosCarrito",
                                        color = Color.White,
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }
                        ) {
                            Icon(
                                icons[index],
                                contentDescription = item
                            )
                        }
                    } else {
                        Icon(
                            icons[index],
                            contentDescription = item
                        )
                    }
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate("listaProductos")
                        2 -> navController.navigate("carrito")
                    }
                }
            )
        }
    }
}
