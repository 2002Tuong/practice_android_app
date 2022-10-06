package com.example.multitablesinroom
import android.content.Context
import androidx.room.Database
import androidx.room.Room

import com.example.multitablesinroom.entities.*



import androidx.room.RoomDatabase
@Database(entities = [Director::class, School::class, Student::class, Subject::class, StudentSubjectCrossRef::class],
version = 1,
exportSchema = false)
abstract class SchoolDatabase : RoomDatabase(){
    abstract fun schoolDao() : SchoolDao
    companion object {
        @Volatile
        private var INSTANCE : SchoolDatabase? = null
        fun getDatabase(context : Context) : SchoolDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}