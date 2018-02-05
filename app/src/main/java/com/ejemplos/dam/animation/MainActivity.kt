package com.ejemplos.dam.animation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*

import kotlinx.coroutines.experimental.android.UI
import org.jetbrains.anko.toast
import kotlin.coroutines.experimental.CoroutineContext

class MainActivity : AppCompatActivity() {

    //private var expectAnimMove : ExpectAnim = ExpectAnim()
    // dispatches execution onto the Android main UI thread
    private val uiContext: CoroutineContext = UI

    // represents a common pool of shared threads as the coroutine dispatcher
    private val bgContext: CoroutineContext = CommonPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var miMensaje = Mensajes("hola Caracola")
        miMensaje.key = "JJ"
        Mensajes.say(miMensaje.key)



        val intento : Intent = Intent(this,Main2Activity::class.java)
        domottaButton.setOnClickListener {
            startActivity(intento)
        }
        animacion.setOnClickListener {
            tareaSegundoPlano()
            /*
            domotta.animate()
                    //.translationY(250f)
                    .rotationBy(90f)
                    .setDuration(2500L)
                    .start()
            */

            /*val domottaSet = AnimatorInflater.loadAnimator(this, R.animator.clockwise_rotation)
            val textoSet = AnimatorInflater.loadAnimator(this, R.animator.clockwise_rotation)
            domottaSet.setTarget(domotta)
            textoSet.setTarget(animacion)
            val bothAnimatorSet = AnimatorSet()
            bothAnimatorSet.playTogether(domottaSet,textoSet)
            bothAnimatorSet.duration = 500
            bothAnimatorSet.start()
            */

            //domottaSet.start()
            /*if (texto!!.rotation == 0f) {
                expectAnimMove.start()
            } else {
                expectAnimMove.reset()
            }*/
        }
        domotta.setOnClickListener{
            toast("Soy Domotta")
        }
    }
    var j : Int = 1
    var job2 : Job? = null
    // launch coroutine in UI context
    private fun tareaSegundoPlano() = launch(uiContext) {
        // tarea child en este contexto
        val task2 = async(bgContext) {
            for (i in 30 downTo 1) { // countdown from 10 to 1
                //texto2.text = "Countdown $i ..." // update text
                Log.d("Task2", "Contandor: ${i}")
                delay(200) // wait half a second
            }
        }
        job2 = task2
        // non ui thread (child task)
        val result = task2.start()
        // tambien podemos job2?.start()
        // domotta bajando/subiendo
        j *= -1
        // animamos a domotta, main task in UI
        val objectAnimator = ObjectAnimator.ofFloat(
                domotta,
                "translationY",
                300f*j)
        objectAnimator.duration = 3000L
        objectAnimator.interpolator
        objectAnimator.start()

        for (i in 10 downTo 1) { // countdown from 10 to 1
            texto.text = "Countdown $i ..." // update text
            delay(500) // wait half a second

            // si la animacion para paro la tarea 2
            if (!objectAnimator.isRunning) job2!!.cancel()
        }

        texto.text = "Done!"
    }
}
