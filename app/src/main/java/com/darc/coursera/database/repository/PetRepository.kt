package com.darc.coursera.database.repository

import android.app.Application
import com.darc.coursera.database.Database
import com.darc.coursera.database.dao.PetDao
import com.darc.coursera.database.entities.Pet
import org.jetbrains.anko.doAsync

class PetRepository(application: Application)  {

    private var languageDao : PetDao = Database.getInstance(application).getPet()

    fun insert(pet: Pet) {
        doAsync {
            val insert = languageDao.insert(pet)
            pet.id = insert
        }
    }

    fun update(pet: Pet){
        doAsync {
            languageDao.update(pet)
        }
    }


    fun delete(pet: Pet){
        doAsync {
            languageDao.delete(pet)
        }
    }

    fun getPets() = languageDao.getPets()

    fun getPetsTopFive() = languageDao.getPetsTopFive()
}