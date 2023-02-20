package com.gyanhub.jobportal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [JobTableEntity::class,UserEntity::class], version =4)
abstract class DataBase : RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {

        val migration_1_2 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Contact  ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1) ")
            }

        }

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            synchronized(this) {
                if (INSTANCE == null) {
                    synchronized(this) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DataBase::class.java,
                            "Job_db"
                        ).allowMainThreadQueries().addMigrations(migration_1_2).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}