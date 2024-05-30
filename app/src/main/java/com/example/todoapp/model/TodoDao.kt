package com.example.todoapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo) // vararg bisa berisi banyak mirip seperti arry namun didalm nya ada todo 1,todo 2, dll

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

//    @Update
//    fun updateTodo(vararg todo: Todo)

    @Delete
    fun deleteTodo(todo:Todo)

    //ada dua versi terkait dengan update pada database nya
    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Update
    fun updateTodo(vararg todo: Todo)

}