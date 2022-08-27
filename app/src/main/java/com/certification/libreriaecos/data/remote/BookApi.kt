package com.certification.libreriaecos.data.remote

import com.certification.libreriaecos.data.model.BookDetail
import com.certification.libreriaecos.data.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {
    @GET("books/")
    suspend fun getBooksData(): Response<List<Book>>

    @GET("book_detail/{id}/")
    suspend fun getBookDetail(@Path("id") id:Int): Response<BookDetail>
}