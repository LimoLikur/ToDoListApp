package com.example.todolist.domain

data class Todo(
    val id: Long,
    val title: String,
    val isDone: Boolean = false
)
