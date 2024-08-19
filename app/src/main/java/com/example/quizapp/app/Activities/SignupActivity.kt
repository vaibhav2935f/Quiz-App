package com.example.quizapp.app.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser(){
        val email:String = binding.etEmail.text.toString()
        val password:String = binding.etPassword.text.toString()
        val confirmPassword:String = binding.etConfirm.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and PAssword can't be Blank", Toast.LENGTH_SHORT).show()
        }
        if (password != confirmPassword) {
            Toast.makeText(this,"Password and Confirm Password doed'nt match",Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email ,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Error creating user", Toast.LENGTH_SHORT).show()

                }
            }
    }
}