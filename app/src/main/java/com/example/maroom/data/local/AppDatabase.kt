package com.example.maroom.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.maroom.data.model.UserEntity
import com.example.maroom.data.model.UserSecondEntity

@Database(entities = [UserEntity::class,UserSecondEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{

        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            INSTANCE = INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_table"
            ).fallbackToDestructiveMigration().build()

            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}