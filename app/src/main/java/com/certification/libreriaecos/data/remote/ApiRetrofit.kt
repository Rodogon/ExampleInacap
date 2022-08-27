package com.certification.libreriaecos.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {
    companion object{
        private const val BASE_URL = "https://ecos-book-api.herokuapp.com/"
        fun retrofitInstance(): BookApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BookApi::class.java)
        }
    }
}