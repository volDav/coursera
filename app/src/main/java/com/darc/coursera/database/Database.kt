package com.darc.coursera.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.darc.coursera.database.dao.PetDao
import com.darc.coursera.database.entities.Pet
import org.jetbrains.anko.doAsync

@androidx.room.Database(entities = [Pet::class],version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {


    companion object {

        lateinit var  instance : Database

        @Synchronized
        fun getInstance(context: Context) : Database {
            if(!this::instance.isInitialized){
                instance = Room.databaseBuilder(context.applicationContext, Database::class.java,"dbpets")
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries()
                    .addCallback(roomCalback)
                    .build()
            }
            if(!this::instance.isInitialized)
                throw Throwable("Inicializacion incorrecta")
            return instance
        }

        //Solo se ejecuta cuando se crea la base de datos por primera vez, es decir cuando no existe
        private val roomCalback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                doAsync {
                    val pets = instance.getPet()

                    pets.let {
                        it.insert(Pet(likes = 0,nombre = "Lucas"))
                        it.insert(Pet(likes = 0,nombre = "Firulais"))
                        it.insert(Pet(likes = 0,nombre = "Boni"))
                        it.insert(Pet(likes = 0,nombre = "Mateo"))
                        it.insert(Pet(likes = 0,nombre = "Onix"))
                        it.insert(Pet(likes = 0,nombre = "Nala"))
                        it.insert(Pet(likes = 0,nombre = "Jachi"))
                        it.insert(Pet(likes = 0,nombre = "Lulu"))
                    }

                }
            }
        }
    }

    abstract fun getPet() : PetDao
}
