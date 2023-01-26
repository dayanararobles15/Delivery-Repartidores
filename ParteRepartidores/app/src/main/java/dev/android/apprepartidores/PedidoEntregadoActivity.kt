package dev.android.apprepartidores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_opciones.*
import kotlinx.android.synthetic.main.activity_pedido_entregado.*

class PedidoEntregadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_entregado)
        clickBoton()
    }
    fun clickBoton(){
        btnListo.setOnClickListener {
           finish();
        }
    }
}