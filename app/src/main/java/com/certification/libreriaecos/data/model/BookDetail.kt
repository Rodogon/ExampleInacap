package com.certification.libreriaecos.data.model

data class BookDetail (
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

/*
"id": 1,
  "author": "Chinua Achebe",
  "country": "Nigeria",
  "imageLink": "https://user-images.githubusercontent.com/22780253/103938792-90279200-5109-11eb-906a-50ac3b73e40d.jpg",
  "language": "English",
  "link": "https://en.wikipedia.org/wiki/Things_Fall_Apart\n",
  "pages": 209,
  "title": "Things Fall Apart",
  "year": 1958,
  "price": 12500,
  "lastPrice": 17500,
  "delivery": true
 */