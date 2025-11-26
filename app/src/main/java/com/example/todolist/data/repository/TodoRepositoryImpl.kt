package com.example.todolist.data.repository

import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.local.TodoEntity
import com.example.todolist.domain.Todo
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override fun getTodos(): Flow<List<Todo>> =
        dao.getTodos().map { list ->
            list.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    isDone = entity.isDone
                )
            }
        }

    override suspend fun addTodo(title: String) {
        val trimmed = title.trim()
        if (trimmed.isBlank()) return

        dao.insertTodo(
            TodoEntity(title = trimmed)
        )
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(
            TodoEntity(
                id = todo.id,
                title = todo.title,
                isDone = todo.isDone
            )
        )
    }

    override suspend fun toggleTodo(todo: Todo) {
        dao.updateTodoStatus(todo.id, !todo.isDone)
    }
}
