package com.darc.coursera.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.darc.coursera.database.entities.Pet

@Dao
interface PetDao {

    @Insert
    fun insert(pet : Pet) : Long

    @Update
    fun update(pet : Pet)

    @Delete
    fun delete(pet : Pet)

    @Query("SELECT * FROM pet")
    fun getPets() : LiveData<List<Pet>>

}