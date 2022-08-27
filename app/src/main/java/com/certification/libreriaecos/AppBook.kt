package com.certification.libreriaecos

import android.app.Application
import com.certification.libreriaecos.data.BookRepository
import com.certification.libreriaecos.data.local.BooksDataBase

class AppBook : Application() {

    private val dataBase by lazy { BooksDataBase.getDataBase(this) }
    val repository by lazy { BookRepository(dataBase.getBookDao()) }
}