package dev.android.apprepartidores.entidades

class clientesDirecciones
data class clienteItem(

var idClienteDireccion: Int,
var direccion: String,
var idClienteDireccionNavigation :clienteItem,
var idClienteNavigation:clientePersona

)
