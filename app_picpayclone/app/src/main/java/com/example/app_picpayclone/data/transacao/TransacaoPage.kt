package com.example.app_picpayclone.data.transacao

import com.example.app_picpayclone.data.Pageable
import com.example.app_picpayclone.data.Sort

data class TransacaoPage(
    val content: List<Transacao>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)