package com.example.todolist.domain.repository

import com.example.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun addTodo(title: String)
    suspend fun deleteTodo(todo: Todo)
    suspend fun toggleTodo(todo: Todo)
}
