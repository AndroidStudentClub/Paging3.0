package ru.mikhailskiy.paging.network


import retrofit2.http.GET
import retrofit2.http.Query
import ru.mikhailskiy.paging.BuildConfig
import ru.mikhailskiy.paging.data.MoviesResponse

interface MovieApiInterface {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DATABASE_API,
        @Query("language") language: String = "ru",
        @Query("page") page: Int
    ): MoviesResponse
}
