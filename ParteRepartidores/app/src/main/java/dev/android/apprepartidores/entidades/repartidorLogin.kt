package dev.android.apprepartidores.entidades

class repartidorLogin :ArrayList<RepartidorItem>()

data class RepartidorItem(
    var idRepartidor: Int,
    var nombre: String,
    var apellido:String

)