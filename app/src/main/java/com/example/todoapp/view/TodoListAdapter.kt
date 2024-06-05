package com.example.todoapp.view

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.ListTodoViewModel


class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick: (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val binding= TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        // penggunaan data binding untuk ui yang rame jadi bisa mempersingkat code
        val todo = todoList[position]
        holder.binding.todo = todo
        holder.binding.listener = this
        holder.binding.editlistener = this

//        ini di ubah karena udah mnggunakan dataBinding
//        holder.binding.checkTask.text = todoList[position].title

//        holder.binding.checkTask.isChecked=false
//        holder.binding.checkTask.setOnCheckedChangeListener {
//                compoundButton, b ->
//            if(compoundButton.isPressed) {
//                adapterOnClick(todoList[position])
//            }
//        }

        holder.binding.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.checkTask.isChecked=false

    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, todo: Todo) {
        if (cb.isPressed) {
            adapterOnClick(todo)
        }
    }

    override fun onEditClick(v: View) {
        val uuid= v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}
