package dev.android.apprepartidores.interfaces


import dev.android.apprepartidores.entidades.RepartidorItem
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @POST("api/login/login")
    fun login(

        @Query("usuario") usuario: String,
        @Query("clave") clave: String,

        ):Call<List <RepartidorItem>>

}