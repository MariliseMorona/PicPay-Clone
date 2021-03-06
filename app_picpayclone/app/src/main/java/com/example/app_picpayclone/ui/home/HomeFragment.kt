package com.example.app_picpayclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.app_picpayclone.Componentes
import com.example.app_picpayclone.ComponentesViewModel
import com.example.app_picpayclone.R
import com.example.app_picpayclone.data.UsuarioLogado
import com.example.app_picpayclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val controlador by lazy { findNavController() }
    private val adapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (UsuarioLogado.isUsuarioNaoLogado()) {
            val direcao = HomeFragmentDirections.actionGlobalNavigationLogin()
            controlador.navigate(direcao)
            return
        }
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        observarSaldo()
        observarTransferencias()
        observarErroSaldo()
        observarErroTransferencia()
        observarLoading()
        configuraRecyclerView()
    }

    private fun observarLoading() {
        homeViewModel.onLoading.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBar.visibility = VISIBLE
                recyclerView.visibility = GONE
            } else {
                progressBar.visibility = GONE
                recyclerView.visibility = VISIBLE
            }
        })
    }

    private fun observarErroTransferencia() {
        homeViewModel.onErrorTransferencia.observe(viewLifecycleOwner, Observer {
            it?.let { mensagem ->
                Toast.makeText(this.context, mensagem, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarErroSaldo() {
        homeViewModel.onErrorSaldo.observe(viewLifecycleOwner, Observer {
            it?.let { mensagem ->
                Toast.makeText(this.context, mensagem, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarTransferencias() {
        homeViewModel.getTransferencias().observe(viewLifecycleOwner, Observer {
            it?.let { transferencias ->
                adapter.submitList(transferencias)
            }
        })
    }

    private fun configuraRecyclerView() {
        recyclerView.adapter = adapter
    }

    private fun observarSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            textViewSaldo.text = it.formatarMoeda()
        })
    }
}