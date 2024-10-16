package com.example.tienda.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tienda.Model.Producto
import com.example.tienda.R


class ProductosViewModel : ViewModel() {

    private val _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> = _productos

    private var currentId = 0

    init {
        _productos.value = listOf(
            Producto(currentId++, "Zapatilla Mujer Rosada", 30000.0, R.drawable.foto1),
            Producto(currentId++, "Zapatilla Hombre Azul", 25000.0, R.drawable.foto2),
            Producto(currentId++, "Zapatilla unisex Verde", 50000.0, R.drawable.foto3),
            Producto(currentId++, "Zapato Casual Hombre Negro", 89.99, R.drawable.foto4),
            Producto(currentId++, "Zapato Taco Mujer Negro", 99.99, R.drawable.foto5),
            Producto(currentId++, "Zapato Taco Color Rojo", 109.99, R.drawable.foto6)
        )
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return _productos.value?.find { it.id == id }
    }


    fun agregarProducto(nombre: String, precio: Double, imagen: Int) {
        val nuevoProducto = Producto(
            id = currentId++,
            nombre = nombre,
            precio = precio,
            imagen = imagen
        )
        _productos.value = _productos.value?.plus(nuevoProducto)
    }
}


