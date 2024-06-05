package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



//Entity Annotation
//Room will create “todo” table in database
@Entity
data class Todo(


//    ColumInfo Annotation
//    specify a column name for the field in table database
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "notes")
    var notes: String,
    @ColumnInfo(name = "priority")
    var priority: Int,
    @ColumnInfo(name = "is_done")
    var is_done: Int = 0 //pake integer karena SQLite tidak ada BOOLEAN data type, pake INTEGER dimana 0 berarti false dan 1 true.

) {

//    PrimaryKey Annotation
//    Each entity class requires one primary key. Autogenerate config set to true to let SQLite generate the unique id
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

}