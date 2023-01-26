package dev.android.apprepartidores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_opciones.*

class OpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)

        val extras = intent.extras;

        val idRepartidor = extras!!.getInt("idRepartido");

        btnOpciones.setOnClickListener {
            val opcionesctivity = Intent(this@OpcionesActivity, ListarPedidosActivity::class.java)
            opcionesctivity.putExtra("idRepartido", idRepartidor)
            startActivity(opcionesctivity)
        }


    }

}