package ru.mikhailskiy.paging.ui

import androidx.recyclerview.widget.DiffUtil
import ru.mikhailskiy.paging.data.Movie

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}