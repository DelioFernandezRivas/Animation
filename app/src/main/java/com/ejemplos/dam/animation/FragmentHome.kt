package com.ejemplos.dam.animation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class FragmentHome : Fragment() {
    /**
     * Initialize nuevaInstance for passing parameters
     */
    companion object {
        var fragmentHome = FragmentHome()
        // definimos los argumentos, son los datos para usar entre el fragment y la activity
        fun nuevaInstance(key: String = "Inicio"): FragmentHome {
            // Recibimos variables en el companion
            Log.d("Mensajes", "En el fragment Home recibo: ${key}")
            return fragmentHome
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Mensajes.say("Desde Fragment Home")
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
