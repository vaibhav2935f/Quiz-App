package com.example.quizapp.app.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.app.adapter.OptionAdapter
import com.example.quizapp.app.models.Question
import com.example.quizapp.app.models.Quiz
import com.example.quizapp.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    var quizzes : MutableList<Quiz>? = null
    var questions: MutableMap<String,Question>? = null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        binding.btnPrev.setOnClickListener {
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())
            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }



    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }
    private fun bindViews() {
        binding.btnNext.visibility = View.GONE
        binding.btnPrev.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if (index == 1){ //first question
            binding.btnNext.visibility = View.VISIBLE
        }
        else if (index == questions!!.size){ //last question
            binding.btnPrev.visibility = View.VISIBLE
            binding.btnSubmit.visibility = View.VISIBLE
        }
        else { //middle Question
            binding.btnPrev.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            binding.description.text = it.description
            val optionAdapter = OptionAdapter(this,it)
            binding.optionList.layoutManager = LinearLayoutManager(this)
            binding.optionList.adapter = optionAdapter
            binding.optionList.setHasFixedSize(true)

        }

    }
}