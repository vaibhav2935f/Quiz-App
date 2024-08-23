package com.example.quizapp.app.Activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapp.R
import com.example.quizapp.app.adapter.QuizAdapter
import com.example.quizapp.app.models.Quiz
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateDummyDate()
        setUpView()
    }

    private fun populateDummyDate() {
        quizList.add(Quiz(id = "12-10-2024","12-10-2024"))
        quizList.add(Quiz(id = "13-10-2024","13-10-2024"))
        quizList.add(Quiz(id = "14-10-2024","14-10-2024"))
        quizList.add(Quiz(id = "15-10-2024","15-10-2024"))
        quizList.add(Quiz(id = "16-10-2024","16-10-2024"))
        quizList.add(Quiz(id = "17-10-2024","17-10-2024"))
        quizList.add(Quiz(id = "18-10-2024","18-10-2024"))
        quizList.add(Quiz(id = "19-10-2024","19-10-2024"))
        quizList.add(Quiz(id = "20-10-2024","20-10-2024"))
        quizList.add(Quiz(id = "21-10-2024","21-10-2024"))

    }

    fun setUpView() {
        setUpFirestore()
        setUpRecyclerView()
        setUpDrawerLayout()
    }
        private fun setUpFirestore() {
        firestore =FirebaseFirestore.getInstance()
        val collectionReference: CollectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener {value, error->
            if (value == null || error!= null){
                Toast.makeText(this,"Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("Data",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }

    }
    private fun setUpRecyclerView() {
        adapter = QuizAdapter(this, quizList)
        binding.quizRecycleView.layoutManager = GridLayoutManager(this, 2)
        binding.quizRecycleView.adapter = adapter
    }
    fun setUpDrawerLayout(){
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.mainDrawer,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}