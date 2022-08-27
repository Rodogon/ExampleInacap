package com.certification.libreriaecos.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.certification.libreriaecos.data.local.BookDao
import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal
import com.certification.libreriaecos.data.remote.ApiRetrofit

class BookRepository(private val bookDao: BookDao) {

    private val retrofitService = ApiRetrofit.retrofitInstance()

    private val errorMessage = MutableLiveData<String>()

    fun getAllBooksFromDB(): LiveData<List<BookLocal>>{
        return bookDao.getAllBooks()
    }

    fun getBookById(id: Int): LiveData<BookDetailLocal>{
        return bookDao.getOneBook(id)
    }

    suspend fun fetchAllBooks(){
        val response = retrofitService.getBooksData()
        when(response.isSuccessful){
            true -> {
                if (response.body().isNullOrEmpty()){
                    errorMessage.value = "ERROR IS NULL OR EMPTY"
                }else{
                    Log.d("RODO", "${response.body()}")
                    bookDao.insertAllBooks(fromRemoteToLocalBook(response.body()!!))
                }
            }
            false -> {
                Log.e("${response.body()}", "NOT SUCCESSFUL ${response.errorBody()}")
                errorMessage.value = response.message()
            }
        }
    }

    suspend fun fetchOneBook(id: Int){
        val response = retrofitService.getBookDetail(id)
        when(response.isSuccessful){
            true -> {
                if (response.body() == null) {
                    errorMessage.value = "ERROR IS NULL"
                }else{
                    Log.d("RODO", "${response.body()}")
                    bookDao.insertOneBook(fromRemoteToLocalBookDetail(response.body()!!))
                }
            }
            false -> {
                Log.e("${response.body()}", "NOT SUCCESSFUL ${response.errorBody()}")
                errorMessage.value = response.message()
            }
        }
    }
}