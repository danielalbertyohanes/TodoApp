package com.example.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.util.DB_NAME
import com.example.todoapp.util.MIGRATION_1_2

import com.example.todoapp.util.MIGRATION_2_3


@Database(entities = arrayOf(Todo::class), version =  3) //version disini akan bertambah ketika ada update atau pembaharuan database
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
                DB_NAME).addMigrations(MIGRATION_1_2,MIGRATION_2_3).build()

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