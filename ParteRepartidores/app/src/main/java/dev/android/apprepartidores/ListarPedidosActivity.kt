package dev.android.apprepartidores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.isVisible
import dev.android.apprepartidores.entidades.PedidosItem
import dev.android.apprepartidores.interfaces.PedidoService
import dev.android.apprepartidores.interfaces.RestEngine
import kotlinx.android.synthetic.main.activity_listar_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListarPedidosActivity : AppCompatActivity(), AdapterView.OnItemClickListener {


    var idNumero = arrayListOf<Int>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_pedidos)
        val extras = intent.extras;
        var idRepartidor = extras!!.getInt("idRepartido");
        if (idRepartidor==null){
            Toast.makeText(this@ListarPedidosActivity," Nulo",Toast.LENGTH_SHORT).show()
            return

        }
        listarRepartidor(idRepartidor);

    }

    private fun listarRepartidor(idRepartidor: Int){


        val pedidosService:PedidoService= RestEngine.getRestEngine().create(PedidoService::class.java)
        val resultado: Call<List<PedidosItem>> =pedidosService.listarPedidos(idRepartidor);
        var listaRepartidor= findViewById<ListView>(R.id.listRepartidores);
        val list = arrayListOf<String>();

        val adapter: ArrayAdapter<*>;
        listaRepartidor.setOnItemClickListener(this)

        adapter= ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        listaRepartidor.setAdapter(adapter);
        resultado.enqueue(object: Callback<List<PedidosItem>> {
            override fun onResponse(
                call: Call<List<PedidosItem>>,
                response: Response<List<PedidosItem>>
            )
            {

                val valor=response?.body()?.count();
                var datos =response?.body();
                var cont:Int = 0;

                datos?.forEach {
                    txtNoPedido.isVisible=false;
                    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
                    val fecha=formatter.format(parser.parse(datos.get(cont).fecha))
                    list.add("\n"+"Id: "+datos.get(cont).idPedido.toString()+"\n"+"Fecha: "+fecha +"\n"+"Total: "+"$ "+ datos.get(cont).total);
                    idNumero.add(datos.get(cont).idPedido)
                    cont+=1;
                }


                adapter.notifyDataSetChanged();

            }

            override fun onFailure(call: Call<List<PedidosItem>>, t: Throwable) {
                Toast.makeText(this@ListarPedidosActivity," No Recuperado",Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onItemClick(p0: AdapterView<*>? , p1: View?, p2: Int, p3: Long) {

        val idPedido=idNumero.get(p2);
        val opcionesctivity = Intent(this@ListarPedidosActivity, DetallePedidoActivity::class.java)
        finish();
        opcionesctivity.putExtra("idPedido", idPedido.toString())
        startActivity(opcionesctivity)

    }
}

