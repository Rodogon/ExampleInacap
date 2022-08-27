package com.certification.libreriaecos.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(listBooks: List<BookLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneBook(book: BookDetailLocal)

    @Query("SELECT * FROM book_table")
    fun getAllBooks(): LiveData<List<BookLocal>>

    @Query("SELECT * FROM book_detail_table WHERE id = :id")
    fun getOneBook(id: Int): LiveData<BookDetailLocal>
}