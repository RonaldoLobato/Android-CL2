package com.lobato.myapplication.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.lobato.myapplication.R
import com.lobato.myapplication.core.Usuario
import com.lobato.myapplication.serivces.UsuarioService

class UsuarioAdapter(private val data: MutableMap<Int,Usuario>?) : RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder>(){

    inner class UsuarioHolder(val v: View) : RecyclerView.ViewHolder(v){

        var txtEmail : TextView
        var txtUsuario : TextView

        init {
            txtEmail = v.findViewById(R.id.viewEmailList)
            txtUsuario = v.findViewById(R.id.viewUserList)
        }
        fun binData(data: Usuario) = with(v){
            txtUsuario.text = data.usNombre
            txtEmail.text = data.usCorreo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_usuario,parent,false)
        return UsuarioHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: UsuarioHolder, position: Int) {
        data?.let {
            it.get(position + 1)?.let { it1 -> holder.binData(it1) }
        }
    }
}