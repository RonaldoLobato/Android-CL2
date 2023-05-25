package com.lobato.myapplication.core

class Usuario{

    var usId: Long = 0
    var usNombre: String
    var usCorreo: String
    var usContraseña: String

    constructor(
        usId: Long,
        usNombre: String,
        usCorreo: String,
        usContraseña: String
    ){
        this.usId = usId
        this.usNombre = usNombre
        this.usCorreo = usCorreo
        this.usContraseña = usContraseña
    }constructor(usNombre: String,usCorreo: String,usContraseña: String){
        this.usNombre = usNombre
        this.usCorreo = usCorreo
        this.usContraseña = usContraseña
    }
}