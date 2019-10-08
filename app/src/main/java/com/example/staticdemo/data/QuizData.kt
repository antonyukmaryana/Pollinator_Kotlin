package com.example.staticdemo.data

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable

data class QuizData(
    val QuestionText: String,
    val AnswerA: String,
    val AnswerB: String,
    val AnswerC: String
)