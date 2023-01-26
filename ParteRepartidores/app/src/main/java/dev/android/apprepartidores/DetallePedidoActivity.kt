package dev.android.apprepartidores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import dev.android.apprepartidores.entidades.PedidosItem
import dev.android.apprepartidores.entidades.clienteItem
import dev.android.apprepartidores.entidades.detallePedidosItem
import dev.android.apprepartidores.interfaces.PedidoService
import dev.android.apprepartidores.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_detalle_pedido.*
import kotlinx.android.synthetic.main.activity_opciones.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class DetallePedidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido)
        val extras = intent.extras;
        var idPedido = extras!!.getString("idPedido");

        listarDatos(Integer.parseInt(idPedido));
        listarDetalle(Integer.parseInt(idPedido));


        btnEntregado.setOnClickListener{
            desactivar(Integer.parseInt(idPedido));

        }


    }



    private fun listarDatos(idPedido: Int){

        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val resultado: Call<List<clienteItem>> =pedidosService.listarDatos(idPedido);
        var listaRepartidor= findViewById<ListView>(R.id.listRepartidores);
        val list = arrayListOf<String>();
        val adaptador: ArrayAdapter<*>;
        adaptador= ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listaRepartidor.setAdapter(adaptador);
        resultado.enqueue(object: Callback<List<clienteItem>> {
            override fun onResponse(
                call: Call<List<clienteItem>>,
                response: Response<List<clienteItem>>
            )
            {

                var datos =response?.body();
                var cont:Int = 0;
                datos?.forEach {

                    list.add( "\n"+"Cliente: "+datos.get(cont).idClienteNavigation.nombre  + " " +datos.get(cont).idClienteNavigation.apellido+"\n"+"Dirección: "+datos.get(cont).direccion);
                    cont+=1;
                }

                adaptador.notifyDataSetChanged();
            }

            override fun onFailure(call: Call<List<clienteItem>>, t: Throwable) {

                Toast.makeText(this@DetallePedidoActivity," No Recuperado",Toast.LENGTH_SHORT).show()

            }

        })
    }


    private fun listarDetalle(idPedido: Int){

        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val resultado: Call<List<detallePedidosItem>> =pedidosService.listarDetalle(idPedido);
        var listaDetalle= findViewById<ListView>(R.id.listDetalle);
        val list = arrayListOf<String>();
        val adapter: ArrayAdapter<*>;
        adapter= ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listaDetalle.setAdapter(adapter);
        resultado.enqueue(object: Callback<List<detallePedidosItem>> {
            override fun onResponse(
                call: Call<List<detallePedidosItem>>,
                response: Response<List<detallePedidosItem>>
            )
            {

                var datos =response?.body();
                Log.e("Datos,", datos.toString())
                var cont:Int = 0;
                datos?.forEach {

                    list.add(datos.get(cont).idProductoNavigation.producto +"\n" +"Cantidad: "  +   datos.get(cont).cantidad.toString() +"\n" + "Precio: "  + datos.get(cont).precio);
                    cont+=1;
                }

                adapter.notifyDataSetChanged();
            }

            override fun onFailure(call: Call<List<detallePedidosItem>>, t: Throwable) {

                Toast.makeText(this@DetallePedidoActivity," No Recuperado",Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun desactivar(idPedido: Int) {
        val pedidosService: PedidoService = RestEngine.getRestEngine().create(PedidoService::class.java)
        val resultado: Call<ResponseBody> =pedidosService.actualizarEstado1(idPedido);

        resultado.enqueue(object:Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 finish();

                val intent = Intent(this@DetallePedidoActivity, PedidoEntregadoActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@DetallePedidoActivity,"Error" ,Toast.LENGTH_SHORT).show();
            }

        })

    }









}