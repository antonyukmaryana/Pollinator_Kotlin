package com.example.staticdemo.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginAccountData(
    val Email: String,
    val Password: String

)