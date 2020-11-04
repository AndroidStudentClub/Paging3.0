package ru.mikhailskiy.paging

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import ru.mikhailskiy.paging.data.Movie
import ru.mikhailskiy.paging.data.MovieRepository
import ru.mikhailskiy.paging.data.paging.MoviesPagingSource
import ru.mikhailskiy.paging.ui.DataLoadingState

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    val movieLoadingStateLiveData = MutableLiveData<DataLoadingState>()

    fun getMovies(): Flow<PagingData<Movie>> {
        return repository.getMovies().cachedIn(viewModelScope)
    }

    fun onMovieClicked(movie: Movie) {
        // TODO handle navigation to details screen event
    }
}