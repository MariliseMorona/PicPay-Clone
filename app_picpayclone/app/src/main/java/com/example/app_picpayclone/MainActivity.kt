package com.example.app_picpayclone

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app_picpayclone.ui.componente.ComponenteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.viewmodel.ext.android.viewModel


// Uma única activity

class MainActivity : ComponentActivity() {

    private val controlador by lazy {
        findNavController(R.id.nav_host_fragment)
    }
    private val componenteViewModel: ComponenteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
       // val navController = findNavController(R.id.nav_host_fragment_container)
       // navView.setupWithNavController(navController)
        componenteViewModel._componentes.observe(this, Observer {
            it?.let{
                temComponentes ->
                if(temComponentes.bottomNavigation){
                    navView.visibility = VISIBLE
                }else{
                    navView.visibility = GONE
                }
            }
        })
        //navView.setupWithNavController(navController)
        navView.setupWithNavController(controlador)
    }
}