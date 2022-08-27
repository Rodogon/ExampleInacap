package com.certification.libreriaecos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.certification.libreriaecos.R
import com.certification.libreriaecos.data.local.entitties.BookLocal
import com.certification.libreriaecos.databinding.BooksItemBinding
import com.squareup.picasso.Picasso


class BooksAdapter(var itemClick: OnItemClickListener) : ListAdapter<BookLocal, BooksAdapter.BookLocalViewHolder>(BooksComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookLocalViewHolder {
        return BookLocalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookLocalViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            itemClick.onItemClick(current)
        }
    }

    class BookLocalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = BooksItemBinding.bind(itemView)
        fun bind(bookLocal: BookLocal){
            binding.tvauthor.text = bookLocal.author
            binding.tvtitle.text = bookLocal.title
            Picasso.get()
                .load(bookLocal.imageLink)
                .resize(100, 150)
                .into(binding.ivimage)

        }


        companion object{
            fun create(parent: ViewGroup): BookLocalViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.books_item, parent, false)
                return BookLocalViewHolder(view)
            }
        }

    }

    class BooksComparator : DiffUtil.ItemCallback<BookLocal>() {
        override fun areItemsTheSame(oldItem: BookLocal, newItem: BookLocal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookLocal, newItem: BookLocal): Boolean {
            return oldItem.id == newItem.id
        }

    }


}