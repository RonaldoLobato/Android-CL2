package com.lobato.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.lobato.myapplication.adapters.UsuarioAdapter
import com.lobato.myapplication.core.Usuario
import com.lobato.myapplication.serivces.UsuarioService

class RegistrarUsuarios : AppCompatActivity() {

    private lateinit var btnCancelar: Button
    private lateinit var btnRegistrar: Button
    private lateinit var txtEmailFrm: TextView
    lateinit var txtUserFrm: EditText
    lateinit var txtPasswordFrm1: EditText
    lateinit var txtPasswordFrm2: EditText

    var usuarioService = UsuarioService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        btnCancelar = findViewById(R.id.btnCancelar)

        btnCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        txtEmailFrm = findViewById(R.id.txtEmailFrom)
        txtUserFrm = findViewById(R.id.txtUserFrom)
        txtPasswordFrm1 = findViewById(R.id.txtPasswordFrom)
        txtPasswordFrm2 = findViewById(R.id.txtConfirmPasswordFrom)

        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener { view ->

            if (validarCampos()) {

                val txtEmail = txtEmailFrm.text.toString()
                val txtUser = txtUserFrm.text.toString()
                val txtPasssword = txtPasswordFrm1.text.toString()

                val usuario = Usuario(txtUser,txtEmail,txtPasssword)

                val builder: AlertDialog.Builder? = view?.let {
                    AlertDialog.Builder(this)
                }
                builder?.setMessage(
                    "Esta a punto de registrar al nuevo usuario"
                )
                    ?.setTitle("¿Desea agregar al nuevo usuario?")

                builder?.setPositiveButton(R.string.add,
                    DialogInterface.OnClickListener { dialog, id ->
                        limpiarFormulario()
                        Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_LONG).show()
                        registrar(usuario)
                    })
                builder?.setNegativeButton(R.string.kick,
                    DialogInterface.OnClickListener { dialog, id ->
                        limpiarFormulario()
                        Toast.makeText(this, "Registro cancelado", Toast.LENGTH_LONG).show()
                    })
                val dialog: AlertDialog? = builder?.create()

                dialog?.show()
            }
        }
    }

    //Validando los campos de texto
    fun validarCampos(): Boolean {
        val email = txtEmailFrm.text.toString().trim()
        val usuario = txtUserFrm.text.toString().trim()
        val password = txtPasswordFrm1.text.toString().trim()
        val passwordConfirm = txtPasswordFrm2.text.toString().trim()

        if (email.isEmpty()) {
            txtEmailFrm.error = "Ingresar un correo electronico"
            return false
        }
        if (usuario.isEmpty()) {
            txtUserFrm.error = "Ingresar un usuario"
            return false
        }
        //Validando la contraseña
        if (password.isEmpty()) {
            txtPasswordFrm1.error = "Ingresar una contraseña"
            return false
        }
        if (passwordConfirm.isEmpty()) {
            txtPasswordFrm2.error = "Confirmar la contraseña"
            return false
        }
        if (password != passwordConfirm) {
            txtPasswordFrm2.error = "Las contraseñas no coinciden"
            return false
        }
        return true
    }

    fun registrar(v: Usuario ) {
        usuarioService.insertarNuevoUsuario(v)
    }
    fun limpiarFormulario(){
        txtUserFrm.setText("")
        txtEmailFrm.setText("")
        txtPasswordFrm1.setText("")
        txtPasswordFrm2.setText("")
    }
}












