package com.lobato.myapplication.serivces

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.lobato.myapplication.config.BaseDatos
import com.lobato.myapplication.config.TABLE_USUARIO_NAME
import com.lobato.myapplication.core.Usuario

class UsuarioService(context: Context){
    val dbHelper = BaseDatos(context)

    //Insertar nuevo registro -- Por confirmar
    fun insertarNuevoUsuario(usuario: Usuario): Usuario? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("usNombre", usuario.usNombre)
            put("usCorreo",usuario.usCorreo)
            put("usContraseña",usuario.usContraseña)
        }
        val newRowId = db?.insert(
            TABLE_USUARIO_NAME,
            null,
            values
        )
        cerrarDB()
        return if (newRowId != null) getUsuario(newRowId.toLong()) else null
    }

    fun getUsuario(idUsuario: Long): Usuario?{
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            "usNombre",
            "usCorreo",
            "usContraseña"
        )
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf("$idUsuario")
        val cursor = db.query(
            TABLE_USUARIO_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        with(cursor){
            if(cursor.count == 1){
                while(moveToNext()) {
                    val usNombre = getString(getColumnIndexOrThrow("usNombre"))
                    val usCorreo = getString(getColumnIndexOrThrow("usCorreo"))
                    val usContrasena = getString(getColumnIndexOrThrow("usContraseña"))
                    val usuarioId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    val usuarioObj = Usuario(
                        usuarioId,
                        usNombre,
                        usCorreo,
                        usContrasena
                    )
                    cerrarDB()
                    return usuarioObj
                }
            } else {
                return null
            }
        }
        return null
    }

    fun getUsuarios(): MutableMap<Int, Usuario>{
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            "usNombre",
            "usCorreo",
            "usContraseña"
        )
        val cursor = db.query(
            TABLE_USUARIO_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )
        val usuarioObjs = mutableMapOf<Int, Usuario>()
        with(cursor) {
            while (moveToNext()) {
            val index = getInt(getColumnIndexOrThrow(BaseColumns._ID))
            val usNombre = getString(getColumnIndexOrThrow("usNombre"))
            val usCorreo = getString(getColumnIndexOrThrow("usCorreo"))
            val usContrasena = getString(getColumnIndexOrThrow("usContraseña"))
            val usuarioId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
            val usuarioObj = Usuario(
                usuarioId,
                usNombre,
                usCorreo,
                usContrasena
            )
                usuarioObjs.put(index,usuarioObj)
            }
        }
        cerrarDB()
        return usuarioObjs
    }

    //Funcion cerrar la BD
    fun cerrarDB(){
        dbHelper.close()
    }
}
