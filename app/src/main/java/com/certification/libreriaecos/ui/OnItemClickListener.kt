package com.certification.libreriaecos.ui

import com.certification.libreriaecos.data.local.entitties.BookLocal

interface OnItemClickListener {
    fun onItemClick(book: BookLocal)
}