package com.dogood.tamilblogs.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dogood.tamilblogs.repository.AppApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DiscoverViewModel:ViewModel() {
    val api_key="55920c043c993d39026c3293f309ca20"

    private val _discoverMoviesResponse= MutableLiveData<String>()

    val discoverMoviesResponse: LiveData<String>
        get() = _discoverMoviesResponse

    private val viewmodeljob= Job()
    private val coroutineScope= CoroutineScope(viewmodeljob+Dispatchers.Main)

    init {
        getMovies()
    }

    fun getMovies(){

        coroutineScope.launch {
            var getMovies=AppApiService.AppApi.retrofitService.discoverMovie(api_key)
            try{
                var movies=getMovies.await()
                _discoverMoviesResponse.value=movies.toString()
            }catch (e:Exception){
                _discoverMoviesResponse.value=e.toString()
            }
        }

    }

}