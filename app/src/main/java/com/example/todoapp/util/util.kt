package com.example.todoapp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.model.TodoDatabase

val DB_NAME = "newtododb"
val MIGRATION_1_2= object : Migration (1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Adding the 'is_done' column as an INTEGER to represent boolean values
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL") // karena SQLite tidak ada BOOLEAN data type, pake INTEGER dimana 0 berarti false dan 1 true.
    }
}

fun buildDb(context: Context): TodoDatabase{
    val db= TodoDatabase.buildDatabase(context)
    return db
}

