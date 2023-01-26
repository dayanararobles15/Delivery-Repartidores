package dev.android.apprepartidores.interfaces

import dev.android.apprepartidores.entidades.PedidosItem
import dev.android.apprepartidores.entidades.RepartidorItem
import dev.android.apprepartidores.entidades.clienteItem
import dev.android.apprepartidores.entidades.detallePedidosItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PedidoService {

    @GET("api/Pedidos/listarPedidosRepartidor")
    fun listarPedidos(
        @Query("id") id:Int
        ): Call<List<PedidosItem>>



    @GET("api/ClientesDirecciones/datos")
    fun listarDatos(
        @Query("id") id:Int
    ): Call<List<clienteItem>>





    @GET("api/DetallePedidos/{id}")
    fun listarDetalle(
        @Path("id") id:Int
    ): Call<List<detallePedidosItem>>


    @POST("api/Pedidos/actualizarEstado")
    fun actualizarEstado1(
        @Query("idPedido") id:Int
    ): Call<ResponseBody>
















}