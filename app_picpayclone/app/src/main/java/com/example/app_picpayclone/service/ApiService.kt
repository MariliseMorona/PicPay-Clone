package com.example.app_picpayclone.service

import com.example.app_picpayclone.data.Usuario
import com.example.app_picpayclone.data.transacao.Transacao
import com.example.app_picpayclone.data.transacao.TransacaoPage
import retrofit2.http.*

interface ApiService {

    @GET("/usuarios/contatos")
    suspend fun getTodosUsuarios(@Query("login") login: String): List<Usuario>

    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String): Usuario

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao): Transacao

    @GET("/transacoes")
    suspend fun getTransacoes(
        @Query("login") login: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): TransacaoPage

}