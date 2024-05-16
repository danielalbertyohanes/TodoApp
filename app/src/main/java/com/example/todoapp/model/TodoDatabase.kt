package com.example.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Todo::class), version =  1) //(Todo::class) sesuai dengan table yang ada di database nya
abstract class TodoDatabase: RoomDatabase() {

    //sususai dengan banyak nya dao yang kita punya kalo kita punya banyak dao maka sebanyak itulah abstract fun todoDao(): TodoDao  harus di buat
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "newtododb").build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }

}