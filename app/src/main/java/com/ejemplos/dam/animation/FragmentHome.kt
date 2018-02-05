package com.ejemplos.dam.animation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class FragmentHome : Fragment() {
    // TODO cancelar tarea al onResume del fragment
    var job: Job? = null

    /**
     * Initialize newInstance for passing paameters
     */
    companion object {
        // definimos los argumentos, son los datos para usar entre el fragment y la activity
        fun newInstance(key: String): FragmentHome {
            var fragmentHome = FragmentHome()
            var args = Bundle()
            args.putString("key",key)
            fragmentHome.arguments = args
            return fragmentHome
        }
    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }
    var jobHome: Job? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobHome = launch(UI) { // launch coroutine in UI context
            for (i in 10 downTo 1) { // countdown from 10 to 1
                textArg.text = "Countdown $i ..." // update text
                delay(500) // wait half a second
            }
            textArg.text = "Done!"
            Mensajes.say("Hola como estas,desde Launch")
        }
        jobHome?.start()
        Mensajes.say("Hola como estas,desde FHome")

        //textArg.setText(arguments.getString("key"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobHome?.cancel()
    }


}// Required empty public constructor
