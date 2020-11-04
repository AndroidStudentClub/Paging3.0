package ru.mikhailskiy.paging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.mikhailskiy.paging.data.paging.MoviesPagingSource

class MovieRepository {

    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(20, enablePlaceholders = false)) { MoviesPagingSource() }.flow
    }
}