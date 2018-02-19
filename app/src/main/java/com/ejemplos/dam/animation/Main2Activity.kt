package com.ejemplos.dam.animation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class Main2Activity : AppCompatActivity() {
    // contador auxiliar
    var count : Int = 0

    // Bundle para pasarle datos al Fragment
    var mArgs = Bundle();

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                // Cargamos Fragment Home
                count += 1
                val fragment = FragmentHome.Companion.nuevaInstance("desde la activity ${count}")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                // Cargamos Fragment DashBoard
                val dFragment = FragmentDashboard()

                mArgs.putString("descripcion", "Un dato desde la Activity mediante Bundler");
                dFragment.setArguments(mArgs)
                addFragment(dFragment)
                //textViewDashboard.setText("Desde Activity")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                // Solo actualizamos texto
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_another -> {
                // Solo actualizamos texto
                message.setText(R.string.title_another)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    /**
     * add/replace fragment in container [framelayout]
     */
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        // Por defecto cargamos fragment Home
        addFragment(FragmentHome.Companion.nuevaInstance("Por defecto cargamos Home"))
    }
}
