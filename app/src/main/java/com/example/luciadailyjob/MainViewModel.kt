package com.example.luciadailyjob

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repository: LuciaJobRepository) : ViewModel() {

    fun insert(luciaJob: LuciaJob) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(luciaJob)
    }

    val onButtonClick:(LuciaJob) -> Unit = {
        viewModelScope.launch(Dispatchers.IO) {
            it.itemStatus = if(it.itemStatus == FinishStatus.NOT_DONE) FinishStatus.DONE else FinishStatus.NOT_DONE
            repository.update(it)
        }
    }

    val allJobs: LiveData<List<LuciaJob>> = repository.allJobs.asLiveData()
}

class MainViewModelFactory(private val repository: LuciaJobRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}