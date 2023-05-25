package com.lobato.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lobato.myapplication.config.BaseDatos
import java.nio.file.Files

class MainActivity : AppCompatActivity() {

    val dbHelper = BaseDatos(this)

    private lateinit var btnRegistrarFrom: Button
    private lateinit var btnIngresar: Button

    lateinit var txtEmail: EditText
    lateinit var txtContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRegistrarFrom = findViewById(R.id.registarFrm)

        btnIngresar = findViewById(R.id.btnIngresar)

        btnIngresar.setOnClickListener{
            val intent = Intent(this,ListarUsuarios::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener{
            txtEmail = findViewById(R.id.txtEmail)
            txtContraseña = findViewById(R.id.txtPassword)

            val usCorreo = txtEmail.text.toString()
            val usContraseña = txtContraseña.text.toString()

            val isvalid = validarUsuario(usCorreo,usContraseña)
            if(isvalid){
                    val intent = Intent(this,ListarUsuarios::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Credencias incorrectos",Toast.LENGTH_LONG).show()
            }
        }

        btnRegistrarFrom.setOnClickListener{
            startActivity(Intent(this,RegistrarUsuarios::class.java))
        }
    }

    fun validarUsuario(usCorreo: String, usContraseña: String) : Boolean{
        val db = dbHelper.readableDatabase

        val colums = arrayOf("usCorreo","usContraseña")
        val selection = "usCorreo=? AND usContraseña=?"
        val selectionArgs = arrayOf(usCorreo, usContraseña)

        val cursor = db.query("tb_usuarios",colums,selection,selectionArgs,null,null,null)

        val resultado = cursor.moveToFirst()

        cursor.close()
        db.close()

        return resultado
    }
}