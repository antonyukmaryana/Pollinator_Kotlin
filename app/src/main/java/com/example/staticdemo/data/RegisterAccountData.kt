package com.example.staticdemo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterAccountData(
    val Email: String,
    val Password: String,
    val ConfirmPassword: String
)