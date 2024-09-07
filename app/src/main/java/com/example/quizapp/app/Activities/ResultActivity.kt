package com.example.quizapp.app.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.app.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        val quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculateScore()
        setAnswerView()

    }

    private fun calculateScore() {

    }

    private fun setAnswerView() {
        TODO("Not yet implemented")
    }
}