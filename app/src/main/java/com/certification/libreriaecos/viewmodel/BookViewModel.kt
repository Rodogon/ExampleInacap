package com.certification.libreriaecos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certification.libreriaecos.data.BookRepository
import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    init {
        fetchAllBooks()
    }

    fun getAllBooksFromDB() : LiveData<List<BookLocal>> {
        return repository.getAllBooksFromDB()
    }

    fun getOneBookFromDB(id: Int) : LiveData<BookDetailLocal>{
        return repository.getBookById(id)
    }

    fun fetchAllBooks() = viewModelScope.launch {
        repository.fetchAllBooks()
    }



}