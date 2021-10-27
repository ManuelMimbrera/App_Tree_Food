package com.example.practica1.ui.cuenta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practica1.R
import com.example.practica1.ui.cuenta.UsuarioFragment
import com.google.gson.Gson


class UsuarioAdapter (val datos:Array<UsuarioFragment.datosUsuarios>): RecyclerView.Adapter<CustomView>(){
    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        var layoutInflater = LayoutInflater.from(parent?.context)
        var cellForRow = layoutInflater.inflate(R.layout.usuario_row_layout, parent, false)
        return CustomView(cellForRow)
    }
    override fun onBindViewHolder(holder: CustomView, position: Int) {

        var editarUser = holder?.itemView.findViewById(R.id.btn_user_act) as TextView
        var name = holder?.itemView.findViewById(R.id.txt_name) as TextView


        editarUser.setOnClickListener{

            val navController = holder?.itemView?.findNavController()
            var objetojson = Gson()
            var datos = objetojson.toJson(datos[position])

            val bundle = bundleOf("datosUsuarios" to datos)

            navController.navigate(R.id.nav_register_usuarios, bundle)
        }
        name.text = datos[position].name
    }
}

class CustomView(varV: View): RecyclerView.ViewHolder(varV){

}