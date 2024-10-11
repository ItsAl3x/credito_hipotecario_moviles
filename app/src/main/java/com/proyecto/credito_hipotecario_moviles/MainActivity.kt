package com.proyecto.credito_hipotecario_moviles

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val campo1 = findViewById<TextInputEditText>(R.id.et_campo1)
        val campo2 = findViewById<TextInputEditText>(R.id.et_campo2)
        val campo3 = findViewById<TextInputEditText>(R.id.et_campo3)
        val campo4 = findViewById<TextInputEditText>(R.id.et_campo4)
        val boton1 = findViewById<Button>(R.id.bt_simular)
        val solucion = findViewById<TextView>(R.id.tv_resultado)

        boton1.setOnClickListener {

            val valorPrestamo = campo1.text.toString().toDoubleOrNull()
            val ingresoMensual = campo2.text.toString().toDoubleOrNull()
            val plazoAnios = campo3.text.toString().toIntOrNull()
            val tasaInteresAnual = campo4.text.toString().toDoubleOrNull()

            if (valorPrestamo != null && ingresoMensual != null && plazoAnios != null && tasaInteresAnual != null) {
                val tasaInteresMensualPorcentual = (tasaInteresAnual / 12) / 100
                val numeroMesesPrestados = plazoAnios * 12

                val numerador = Math.pow(1 + tasaInteresMensualPorcentual, numeroMesesPrestados.toDouble()) * tasaInteresMensualPorcentual
                val denominador = Math.pow(1 + tasaInteresMensualPorcentual, numeroMesesPrestados.toDouble()) - 1
                val fraccion = numerador / denominador
                val cuotaMensual = fraccion * valorPrestamo

                // Mostrar resultado simulación en el TextView 'solucion'
                solucion.text = String.format("Paga cuotas de: %.2f por mes", cuotaMensual)
            } else {
                // Manejar el caso en que alguno de los campos no sea válido
                solucion.text = "Por favor, ingresa valores válidos en todos los campos."
            }
        }
    }
}

