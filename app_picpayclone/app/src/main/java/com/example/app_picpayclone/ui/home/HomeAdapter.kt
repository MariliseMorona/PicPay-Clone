package com.example.app_picpayclone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_picpayclone.R
import com.example.app_picpayclone.data.transacao.Transacao
import com.example.app_picpayclone.data.transacao.TransacaoDiffUtilCallBack
import com.example.app_picpayclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.item_transacao.view.*

class HomeAdapter : PagedListAdapter<Transacao, HomeAdapter.ViewHolder>(TransacaoDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_transacao,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transacao = getItem(position)
        transacao?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(transacao: Transacao) {
            with(itemView) {
                textViewOrigem.text = transacao.origem.nomeCompleto
                textViewDestino.text = transacao.destino.nomeCompleto
                textViewValor.text = transacao.valor.formatarMoeda()
                textViewData.text = transacao.dataHora
                textViewCirculo.text = transacao.origem.nomeCompleto.first().toUpperCase().toString()
            }
        }
    }

}