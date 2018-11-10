package com.example.user.componentes

class Mock private constructor(){

    //Companion Object possibilita acesso como Static
    companion object {
        fun getList() = listOf("Gramas","Kilo","Arroba","Toneladas")
    }

}