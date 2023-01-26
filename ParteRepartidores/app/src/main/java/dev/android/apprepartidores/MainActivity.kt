package dev.android.apprepartidores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import dev.android.apprepartidores.entidades.RepartidorItem

import dev.android.apprepartidores.interfaces.LoginService
import dev.android.apprepartidores.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener {
            login();


        }


    }



    fun login(){
        var idRepartidor: Int=0;
        var usuariotxt:EditText=findViewById(R.id.edtUsername)
        var clavetxt:EditText=findViewById(R.id.edtPassword)
        val repartidorService:LoginService=RestEngine.getRestEngine().create(LoginService::class.java)
        val resultado: Call<List<RepartidorItem>> =repartidorService.login(usuariotxt.text.toString(),clavetxt.text.toString());

        resultado.enqueue(
            object :Callback<List<RepartidorItem>>{
                override fun onResponse(
                    call: Call<List<RepartidorItem>>,
                    response: Response<List<RepartidorItem>>)
                {
                    var datos=response.body();
                    datos?.forEach {
                        idRepartidor= datos.get(0).idRepartidor;
                    }
                    val opcionesctivity = Intent(this@MainActivity, OpcionesActivity::class.java)
                    opcionesctivity.putExtra("idRepartido", idRepartidor)
                    startActivity(opcionesctivity)
                }

                override fun onFailure(call: Call<List<RepartidorItem>>, t: Throwable) {

                    Toast.makeText(this@MainActivity,"Error de Red y/o de Credenciales",Toast.LENGTH_SHORT).show()

                }


            }
        )







    }




}