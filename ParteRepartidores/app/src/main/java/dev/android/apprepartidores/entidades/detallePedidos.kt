package dev.android.apprepartidores.entidades

data class detallePedidosItem(

    var idDetallePedido: Int,
    var cantidad: Int,
    var precio :Double,
    var idProductoNavigation:productoItem

)
