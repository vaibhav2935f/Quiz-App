package com.example.quizapp.app.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLoginintroBinding
import com.google.firebase.auth.FirebaseAuth

class Loginintro : AppCompatActivity() {
    private lateinit var binding: ActivityLoginintroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginintroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            Toast.makeText(this,"User already Logged in!", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")

        }
    }
    private fun redirect(name:String){
        val intent:Intent = when(name){
            "LOGIN" -> Intent(this, loginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw  Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}