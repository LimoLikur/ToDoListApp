package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.todolist.data.local.TodoDatabase
import com.example.todolist.data.repository.TodoRepositoryImpl
import com.example.todolist.ui.TodoListScreen
import com.example.todolist.ui.TodoViewModel
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Room & Repository
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_db"
        ).build()

        val repository = TodoRepositoryImpl(db.todoDao())

        setContent {
            ToDoListTheme {
                val viewModel: TodoViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            @Suppress("UNCHECKED_CAST")
                            return TodoViewModel(repository) as T
                        }
                    }
                )

                TodoListScreen(viewModel = viewModel)
            }
        }
    }
}
