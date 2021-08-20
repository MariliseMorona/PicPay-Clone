package com.example.app_picpayclone.data

data class Pageable(
    val offset: Int = 0,
    val pageNumber: Int = 0,
    val pageSize: Int = 0,
    val paged: Boolean = true,
    val sort: Sort = Sort(),
    val unpaged: Boolean = true
)
