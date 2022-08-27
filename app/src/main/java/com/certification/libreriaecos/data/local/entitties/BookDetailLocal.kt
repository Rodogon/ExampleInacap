package com.certification.libreriaecos.data.local.entitties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_detail_table")
data class BookDetailLocal (
        @PrimaryKey
        val id: Int,
        val author: String,
        val imageLink: String,
        val language: String,
        val link: String,
        val pages: Int,
        val title: String,
        val year: Int,
        val price: Int,
        val lastPrice: Int,
        val delivery: Boolean
        )