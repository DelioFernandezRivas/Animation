package com.ejemplos.dam.animation

import android.util.Log

/**
 * Created by flia on 4/2/18.
 */
data class Mensajes constructor(private var key2: String = "Hi"){

    var key: String = ""

        get() = field
        set(value) {
            field = value + " :P"
        }

    companion object Compi {
        fun say(message: String) {
            Log.d("datos",message)
        }

        fun write(message: String) {
        }
    }

}