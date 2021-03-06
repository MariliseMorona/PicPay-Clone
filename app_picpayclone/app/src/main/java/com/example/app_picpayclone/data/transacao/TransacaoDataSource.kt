package com.example.app_picpayclone.data.transacao

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.app_picpayclone.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TransacaoDataSource(
    private val apiService: ApiService,
    private val scope: CoroutineScope,
    private val login: String,
    private val onLoading: MutableLiveData<Boolean>,
    private val onError: MutableLiveData<String>
) : PageKeyedDataSource<Int, Transacao>() {

    companion object {
        const val PAGE_SIZE = 5
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Transacao>
    ) {
        scope.launch {
            onLoading.value = true
            try {
                val transacoes = apiService.getTransacoes(login, 0, PAGE_SIZE)
                callback.onResult(transacoes.content, null, 1)
            } catch (e: Exception) {
                onError.value = e.message
            }
            onLoading.value = false
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Transacao>) {
        scope.launch {
            try {
                val page = params.key
                val transacoes = apiService.getTransacoes(login, page, PAGE_SIZE)
                callback.onResult(transacoes.content, page.plus(1))
            } catch (e: Exception) {
                onError.value = e.message
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Transacao>) {
        TODO("Not yet implemented")
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}
