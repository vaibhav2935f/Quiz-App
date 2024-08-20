package com.example.quizapp.app.adapter

import android.content.Context
<<<<<<< HEAD
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
=======
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
>>>>>>> origin/master
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.app.models.Quiz
<<<<<<< HEAD
import com.example.quizapp.app.utils.colorPicker
import com.example.quizapp.app.utils.iconPicker
=======
>>>>>>> origin/master

class QuizAdapter (val context : Context, val quizzes : List<Quiz>):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
<<<<<<< HEAD
        holder.cardContainer.setCardBackgroundColor((Color.parseColor(colorPicker.getColor())))
        holder.iconView.setImageResource(iconPicker.getIcon())
        holder.iconView.setOnClickListener{
            Toast.makeText(context, quizzes[position].title,Toast.LENGTH_SHORT).show()
        }
    }
    inner class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
=======
    }
    inner class  QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.quizIcon)
>>>>>>> origin/master

    }

}