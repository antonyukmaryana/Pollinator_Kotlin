package com.example.staticdemo.data

import com.squareup.moshi.Moshi
import okhttp3.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


object DataSource {
    private val baseUrl = "http://localhost:5000/api/"
    private val ok: OkHttpClient = OkHttpClient()

    val json = Json(JsonConfiguration.Stable)

    private val moshi = Moshi.Builder().build()
    var jsonRegisterAdapter = moshi.adapter(RegisterAccountData::class.java!!)
    var jsonLoginAdapter = moshi.adapter(LoginAccountData::class.java!!)

    fun register(email: String, password: String, confirmPassword: String, callback: Callback) {
        val registerAccountData =
            RegisterAccountData(email, password, confirmPassword)
        val response =
            ok.newCall(
                Request.Builder()
                    .url(baseUrl + "account/register")
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
                    .url(baseUrl + "account/login")
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

    fun createQuiz(
        questionText: String,
        answerA: String,
        answerB: String,
        answerC: String, callback: Callback
    ) {
        val quiz = QuizData(questionText, answerA, answerB, answerC)
        val response =
            ok.newCall(
                Request.Builder()
                    .url(baseUrl + "quiz/create")
                    .post(
                        RequestBody.create(
                            MediaType.parse("application/json"),
                            json.stringify(QuizData.serializer(), quiz)
                        )
                    )
                    .build()
            )
                .enqueue(callback)
    }


}