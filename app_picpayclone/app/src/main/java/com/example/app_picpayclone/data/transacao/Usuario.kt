package com.example.app_picpayclone.data.transacao

import android.os.Parcelable

@Parcelize
data class Usuario(
    val login: String = "",
    val senha: String = "",
    val email: String = "",
    val nomeCompleto: String = "",
    val cpf: String = "",
    val dataNascimento: String = "",
    val numeroTelefone: String = "",
    val saldo: Double = 0.0
): Parcelable
