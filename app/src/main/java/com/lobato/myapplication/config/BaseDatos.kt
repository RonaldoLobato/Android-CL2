package com.lobato.myapplication.config

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

//Creamos el nombre de la tabla de la BD
const val TABLE_USUARIO_NAME = "tb_usuarios"

//Creamos las columnas respectivas
private const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_USUARIO_NAME (" + "${BaseColumns._ID} INTEGER PRIMARY KEY," +  "usNombre TEXT," + "usCorreo TEXT," + "usContraseña TEXT)"
//Eliminamos la tabla si es que existe
private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_USUARIO_NAME"

class BaseDatos(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        //Definimos la versionr
        const val DATABASE_VERSION = 1
        //Definimos el nombre de la BD
        const val DATABASE_NAME = "Temporal.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Insertando un par de registro de prueba
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},usNombre,usCorreo,usContraseña) VALUES" + "(1,'Ronaldo Lobato','ronaldLobato27@outlook.com','12345')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},usNombre,usCorreo,usContraseña) VALUES" + "(2,'Albert Lobato','tylerlobato@gmail.com','12345')")
        db.execSQL("INSERT INTO $TABLE_USUARIO_NAME(${BaseColumns._ID},usNombre,usCorreo,usContraseña) VALUES" + "(3,'Hilda Apaza','hapaza789@gmail.com','12345')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

}









