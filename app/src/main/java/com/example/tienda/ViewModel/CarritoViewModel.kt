package com.example.tienda.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tienda.Model.CarritoItem
import com.example.tienda.Model.Producto

class CarritoViewModel : ViewModel() {
    private val _carrito = MutableLiveData<List<CarritoItem>>()
    val carrito: LiveData<List<CarritoItem>> = _carrito

    init {
        _carrito.value = listOf()
    }

    fun agregarAlCarrito(producto: Producto) {
        val listaActual = _carrito.value ?: listOf()
        val itemExistente = listaActual.find { it.producto.id == producto.id }

        if (itemExistente != null) {
            itemExistente.cantidad += 1
        } else {
            _carrito.value = listaActual + CarritoItem(producto, 1)
        }
    }

    fun removerDelCarrito(producto: Producto) {
        _carrito.value = _carrito.value?.filterNot { it.producto.id == producto.id }
    }

    fun actualizarCantidad(producto: Producto, cantidad: Int) {
        _carrito.value = _carrito.value?.map {
            if (it.producto.id == producto.id) {
                it.copy(cantidad = cantidad)
            } else {
                it
            }
        }
    }

    fun limpiarCarrito() {
        _carrito.value = listOf()
    }
}