package com.example.app_picpayclone.data.transacao

import androidx.recyclerview.widget.DiffUtil

class TransacaoDiffUtilCallBack : DiffUtil.ItemCallback<Transacao>() {
    override fun areItemsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
        return oldItem.codigo == newItem.codigo
    }

    override fun areContentsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
        return oldItem == newItem
    }
}