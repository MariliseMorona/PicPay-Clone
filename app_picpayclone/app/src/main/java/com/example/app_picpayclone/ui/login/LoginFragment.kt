package com.example.app_picpayclone.ui.login

import com.example.app_picpayclone.Componentes
import com.example.app_picpayclone.ComponentesViewModel
import com.example.app_picpayclone.R
import com.example.app_picpayclone.data.UsuarioLogado
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val componenteViewModel: ComponentesViewModel by sharedViewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*Esse item foi substituido pela implementação da classe Componentes
        val bottomNavigation = activity?.findViewById<View>(R.id.nav_host_fragment_container)
        bottomNavigation?.let {
            it.visibility = GONE
        }*/
        componenteViewModel.temComponentes = Componentes(bottomNavigation = false)
        //configurarBotaoLogin()
        configuraLogin()
        observaUsuario()
        observaErro()
    }


    /*private fun configurarBotaoLogin() {
        btn.setOnClickListener {
            UsuarioLogado.usuario = Usuario("joaovf")
            vaiParaHome()
        }*/

    /* private fun vaiParaHome(){
            val direcao =
                LoginFragmentDirectNavigations.actionLoginFragmentToNavigationsHome()
            val controlador = findNavController()
                    controlador.navigate(direcao)
        }*/
    private fun observaErro() {
        loginViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observaUsuario() {
        loginViewModel.usuario.observe(viewLifecycleOwner, Observer {
            UsuarioLogado.usuario = it
            val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
            controlador.navigate(direcao)
        })
    }

    private fun configuraLogin() {
        button.setOnClickListener {
            val usurio = editTextUsuario.text.toString()
            loginViewModel.login(usurio)
        }
    }
}



