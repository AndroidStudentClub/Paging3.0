package ru.mikhailskiy.paging

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mikhailskiy.paging.data.MovieRepository
import ru.mikhailskiy.paging.ui.DataLoadingState
import ru.mikhailskiy.paging.ui.MoviesAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    // Создание адаптера
    private val moviesAdapter = MoviesAdapter()
    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val mainViewModelFactory = MainViewModelFactory(MovieRepository())
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        movies_recycler_view.adapter = moviesAdapter
        // Запрашиваем фильмы
        fetchMovies()

        mainViewModel.movieLoadingStateLiveData.observe(this, Observer {
            onMovieLoadingStateChanged(it)
        })
    }

    // Получение списка фильмов
    private fun fetchMovies() {
        lifecycleScope.launch {
            // Так как collectLatest suspend - функция то вызывать мы можем ее только через корутин билдер
            // В данном случае используется корутин билдер lifecycleScope.launch
            mainViewModel.getMovies().collectLatest { it ->
                moviesAdapter.submitData(it)
            }
        }
    }

    private fun onMovieLoadingStateChanged(state: DataLoadingState) {
        progress_circular.visibility =
            if (state == DataLoadingState.LOADING) View.VISIBLE else View.GONE
    }
}

class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}