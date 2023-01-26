package dev.android.apprepartidores.entidades

class Pedidos :ArrayList<PedidosItem>()

data class PedidosItem(
    var idPedido: Int,
    var fecha: String,
    var idClienteDireccion:Int,
    var total:Double,
    var direccion: String,
    var idClienteDireccionNavigation :clienteItem

)