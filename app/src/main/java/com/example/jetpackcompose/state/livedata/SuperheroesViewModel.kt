package com.example.jetpackcompose.state.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.core.Person
import com.example.jetpackcompose.core.getSuperheroList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SuperheroesViewModel: ViewModel() {
    private val superheroes = MutableLiveData<List<Person>>()

    init {
        loadSuperheroes()
    }

    // We expose the users LiveData that contains a list of person that is used by the activity
    fun getSuperheroes(): LiveData<List<Person>> = superheroes

    // Added a delay of 2 seconds to emulate a network request. This method just sets the list of
    // superheroes to the livedata after 2 seconds.
    fun loadSuperheroes() = Single.fromCallable { getSuperheroList() }
        .delay(2000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map {
            superheroes.value = it
        }
        .subscribe()
}