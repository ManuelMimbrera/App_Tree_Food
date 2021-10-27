package com.example.practica1.ui.productos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practica1.R
import com.example.practica1.base.dbHelper
import com.example.practica1.ui.ventas.DatosVenta
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class ProductosAdapter(val datos: Array<ProductosFragment.datosProducto>): RecyclerView.Adapter<CustomView>() {

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.productos_row_layout, parent,false)
        return CustomView(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {

        var finalizarProd = holder?.itemView.findViewById(R.id.btn_comprarProd) as TextView
        var agregarProd = holder?.itemView.findViewById(R.id.btn_agregar_carrito) as TextView
        var eliminarProd = holder?.itemView.findViewById(R.id.btn_elim_prod) as TextView
        var editarProd = holder?.itemView.findViewById(R.id.btn_act_prod) as TextView
        var nombreProd = holder?.itemView.findViewById(R.id.txt_nombrePro) as TextView
        var descripcionProd = holder?.itemView.findViewById(R.id.txt_descripcionPro) as TextView
        var precioProd = holder?.itemView.findViewById(R.id.txt_precioPro) as TextView


        editarProd.setOnClickListener{
            val navController = holder?.itemView?.findNavController()

            var objJson = Gson()

            var datos = objJson.toJson(datos[position])

            val bundle = bundleOf("datosProduct" to datos)

            navController.navigate(R.id.nav_datos_producto, bundle)
        }

        eliminarProd.setOnClickListener{
            val navController = holder?.itemView?.findNavController()

            var objJson = Gson()

            var datos = objJson.toJson(datos[position])

            val bundle = bundleOf("datosProducto" to datos)

            navController.navigate(R.id.nav_productos_eliminar, bundle)
        }

        agregarProd.setOnClickListener{
            Toast.makeText(holder?.itemView?.context, "Agregado al carrito", Toast.LENGTH_LONG).show()

            var url = "http://10.0.76.173:8000/api/guardar_opciones"

            val jSon = Gson()

            val tipoPet = "application/json; charset=utf-8".toMediaType()

            var datosJsonProd = jSon.toJson(
                datosCarrito(
                    nombreProd.text.toString(),
                    descripcionProd.text.toString(),
                    precioProd.text.toString().toFloat()
                )
            )

            var request = Request.Builder().url(url).post(datosJsonProd.toRequestBody(tipoPet))

            val dbHelp = dbHelper(holder?.itemView?.context as Context)
            val dbRead = dbHelp.readableDatabase
            val cursor = dbRead.query(
                dbHelper.FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,             // don't group the rows
                null,              // don't filter by row groups
                null               // The sort order
            )

            var token = ""

            with(cursor) {
                moveToNext()

                token = getString(getColumnIndexOrThrow(dbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_TOKEN))
            }

            request.addHeader("Accept","application/json")
            request.addHeader("Authorization","Bearer " + token)

            var client = OkHttpClient()

            client.newCall(request.build()).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    println("OK")
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Algo salió mal")
                }
            })
        }

        finalizarProd.setOnClickListener{
            val navController = holder?.itemView?.findNavController()

            var objJson = Gson()

            var datos = objJson.toJson(datos[position])

            val bundle = bundleOf("datosVenta" to datos)

            navController.navigate(R.id.nav_finalizar_ven, bundle)
        }

        nombreProd.text = datos[position].name
        descripcionProd.text = datos[position].descripcion
        precioProd.text = datos[position].precio.toString()
    }
}

data class datosCarrito(

    val producto: String,
    val descri: String,
    val costo: Float
)

class CustomView(varV: View): RecyclerView.ViewHolder(varV){

}