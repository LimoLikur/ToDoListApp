package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.todolist.ui.TodoListScreen
import com.example.todolist.ui.TodoViewModel
import com.example.todolist.ui.theme.ToDoListTheme // sesuaikan dengan nama theme-mu

class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                TodoListScreen(viewModel = todoViewModel)
            }
        }
    }
}
