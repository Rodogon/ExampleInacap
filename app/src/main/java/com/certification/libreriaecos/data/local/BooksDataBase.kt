package com.certification.libreriaecos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal

@Database(
    entities = [BookLocal::class, BookDetailLocal::class],
    version = 1,
    exportSchema = false
)
abstract class BooksDataBase: RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object{
        @Volatile
        private var INSTANCE: BooksDataBase? = null

        fun getDataBase(context: Context): BooksDataBase{
            return INSTANCE ?: synchronized(this){
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDataBase::class.java,
                    "BooksDB"
                ).build()
                INSTANCE = tempInstance
                return tempInstance
            }
        }
    }
}