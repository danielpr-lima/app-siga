package br.com.alura.orgs.models

data class LoginRequest(
    val cpf: String,
    val senha: String // É a senha em texto puro que será enviada para a API fazer o hash
)