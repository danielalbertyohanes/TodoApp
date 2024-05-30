package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment(), RadioClickListener, TodoEditClickListener {
    private lateinit var binding:FragmentEditTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    //private lateinit var todo:Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // bagian di bawah ini yang di ganti kektika ingin menggnukan layout create pada fragment edtTodo
        binding = FragmentEditTodoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

//        binding.btnAdd.setOnClickListener{
//            todo.title=binding.txtTitle.text.toString()
//            todo.notes=binding.txtNotes.text.toString()
//            var radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
//            todo.priority=radio.tag.toString().toInt()
//            viewModel.update(todo)
//            Toast.makeText(context, "Todo Update", Toast.LENGTH_SHORT).show()
//        }
        binding.radioListener = this
        binding.submitListener = this

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            binding.todo=it


        // codingan dibawah ini yang belum menggunakan two way binding
//            todo=it
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)
//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                3 -> binding.radioHight.isChecked = true
//            }

        })
    }

    override fun onRadioClick(v: View) {
//        binding.todo.let {
//            binding.todo?.priority = v.tag.toString().toInt()
//        }
        binding.todo!!.priority = v.tag.toString().toInt()
    }

    override fun onEditClick(v: View) { // ini tombol submit nya
        viewModel.update(binding.todo!!)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }


}