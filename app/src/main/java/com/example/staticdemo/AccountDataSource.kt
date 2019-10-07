package com.example.staticdemo

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

object AccountDataSource {
    private val baseUrl = "http://localhost:5000/api/account/"
    private val ok: OkHttpClient = OkHttpClient()
    private val moshi = Moshi.Builder().build()
    var jsonRegisterAdapter = moshi.adapter(RegisterAccountData::class.java!!)
    var jsonLoginAdapter = moshi.adapter(LoginAccountData::class.java!!)

    fun register(email: String, password: String, confirmPassword: String, callback: Callback) {
        val registerAccountData = RegisterAccountData(email, password, confirmPassword)
        val response =
            ok.newCall(
                Request.Builder()
                    .url(baseUrl + "register")
                    .post(
                        RequestBody.create(
                            MediaType.parse("application/json"),
                            jsonRegisterAdapter.toJson(registerAccountData)
                        )
                    )
                    .build()
            )
                .enqueue(callback)
    }

    fun login(email: String, password: String, callback: Callback) {
        val loginAccountData = LoginAccountData(email, password)
        val response =
            ok.newCall(
                Request.Builder()
                    .url(baseUrl + "login")
                    .post(
                        RequestBody.create(
                            MediaType.parse("application/json"),
                            jsonLoginAdapter.toJson(loginAccountData)
                        )
                    )
                    .build()
            )
                .enqueue(callback)
    }
}