package com.example.app_picpayclone.ui.pagar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_picpayclone.data.Usuario
import com.example.app_picpayclone.data.UsuarioLogado
import com.example.app_picpayclone.service.ApiService
import kotlinx.coroutines.launch

//Classe onde é salva os dados
//Usando o View Model não se perde os dados caso a tela seja rotacionada
class PagarViewModel(private val apiService: ApiService) : ViewModel() {

    private val _contatos = MutableLiveData<List<Usuario>>()
    val contatos: LiveData<List<Usuario>> = _contatos
    val onLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            onLoading.value = true
            try {
                val usuarios = apiService.getTodosUsuarios(UsuarioLogado.usuario.login)
                _contatos.value = usuarios
            } catch (e: Exception) {
                onError.value = e.message
            }
            onLoading.value = false
        }
    }

}