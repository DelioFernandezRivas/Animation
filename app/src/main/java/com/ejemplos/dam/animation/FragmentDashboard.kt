package com.ejemplos.dam.animation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentDashboard : Fragment() {
    /**
     * Initialize nuevaInstance for passing parameters
     */

    // propiedad para recojer datos
    var descripcion : String = "Nada"

    companion object {
        fun newInstance(): FragmentDashboard {
            var fragmentDashboard = FragmentDashboard()
            return fragmentDashboard
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // recojemos lo que nos manda la Activity
        descripcion = arguments.getString("descripcion", "Nada")
        // Inflate the layout for this fragment
        val vistaFragment = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        return vistaFragment
    }

    override fun onResume() {
        super.onResume()
        editTextDashboard.setText(descripcion)
    }



}// Required empty public constructor
