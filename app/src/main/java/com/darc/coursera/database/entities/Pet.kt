package com.darc.coursera.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "pet"
)
data class Pet (

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,

    var likes : Int = 0,

    var nombre: String = ""

)