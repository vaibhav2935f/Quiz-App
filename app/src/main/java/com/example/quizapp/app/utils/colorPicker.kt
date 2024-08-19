package com.example.quizapp.app.utils

object colorPicker {
    val colors = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#036280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#70659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EFSEAD",
        "#BSBFC6"
    )


    var currentColorIndex = 0
    fun getColor(): String {

        currentColorIndex = (currentColorIndex + 1) % colors.size

        return colors [currentColorIndex]

    }
}