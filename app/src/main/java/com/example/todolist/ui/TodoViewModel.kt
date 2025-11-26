package com.example.todolist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todolist.domain.Todo

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val inputText: String = ""
)

class TodoViewModel : ViewModel() {

    var uiState by mutableStateOf(TodoUiState())
        private set

    fun onInputChange(newText: String) {
        uiState = uiState.copy(inputText = newText)
    }

    fun addTodo() {
        val text = uiState.inputText.trim()
        if (text.isBlank()) return

        val newTodo = Todo(
            id = System.currentTimeMillis(),
            title = text
        )

        uiState = uiState.copy(
            todos = listOf(newTodo) + uiState.todos,
            inputText = ""
        )
    }

    fun toggleTodo(id: Long) {
        uiState = uiState.copy(
            todos = uiState.todos.map {
                if (it.id == id) it.copy(isDone = !it.isDone) else it
            }
        )
    }

    fun deleteTodo(id: Long) {
        uiState = uiState.copy(
            todos = uiState.todos.filterNot { it.id == id }
        )
    }
}
