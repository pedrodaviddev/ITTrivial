package com.pedrodavidlp.ittrivial.game.domain.model

data class Question(val question: String = "No question",
                    val answer: String = "Failed",
                    val wans1: String = "Failed",
                    val wans2: String = "Failed",
                    val wans3: String = "Failed")