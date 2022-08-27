package com.certification.libreriaecos.data

import com.certification.libreriaecos.data.local.entitties.BookDetailLocal
import com.certification.libreriaecos.data.local.entitties.BookLocal
import com.certification.libreriaecos.data.model.Book
import com.certification.libreriaecos.data.model.BookDetail

fun fromRemoteToLocalBook(wrapper: List<Book>) : List<BookLocal> {
    return wrapper.map {
        BookLocal(id = it.id,
        author = it.author,
        country = it.country,
        imageLink = it.imageLink,
        language = it.language,
        title = it.title
        )
    }
}

fun fromRemoteToLocalBookDetail(wrapper: BookDetail) : BookDetailLocal {
    return BookDetailLocal(wrapper.id,
    wrapper.author,
    wrapper.imageLink,
    wrapper.language,
    wrapper.link,
    wrapper.pages,
    wrapper.title,
    wrapper.year,
    wrapper.price,
    wrapper.lastPrice,
    wrapper.delivery)
}