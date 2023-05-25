package com.lobato.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.lobato.myapplication.adapters.UsuarioAdapter
import com.lobato.myapplication.core.Usuario
import com.lobato.myapplication.serivces.UsuarioService

class ListarUsuarios : AppCompatActivity() {

    var usuarioService = UsuarioService(this)

    lateinit var listaUsuarios: MutableMap<Int, Usuario>
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuario)

        listaUsuarios = usuarioService.getUsuarios()

        recycler = findViewById(R.id.mi_lista_recycler_view)

        val adapter =  UsuarioAdapter(listaUsuarios)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        recycler.adapter = adapter

    }

}