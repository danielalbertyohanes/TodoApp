package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {

    val todoListLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        loadingLD.value= true
        todoLoadErrorLD.value=false

        //launch ini artinya code dibawah ini bisa di jalan bersamaan dengan code yang lain nya
        launch {
            val db = TodoDatabase.buildDatabase(getApplication())
            todoListLD.postValue(db.todoDao().selectAllTodo())
            loadingLD.postValue(false)
            todoLoadErrorLD.postValue(false)
        }
    }

    fun checkTask(todo: Todo){
        //launch ini artinya code dibawah ini bisa di jalan bersamaan dengan code yang lain nya
        launch {
            val db = TodoDatabase.buildDatabase(getApplication())
            db.todoDao().deleteTodo(todo)
            todoListLD.postValue(db.todoDao().selectAllTodo())

        }
    }
}