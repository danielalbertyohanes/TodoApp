package com.example.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoapp.model.Todo

interface TodoCheckedChangeListener{
    fun onTodoCheckedChange(cb:CompoundButton,isChecked:Boolean,todo: Todo)
}

interface  TodoEditClickListener{
    fun onEditClick(v:View)
}

interface RadioClickListener {
    fun onRadioClick(v:View)
}
