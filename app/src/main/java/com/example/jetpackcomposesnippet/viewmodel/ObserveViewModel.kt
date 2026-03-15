package com.example.jetpackcomposesnippet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ObserveViewModel : ViewModel() {

    private val _livedata = MutableLiveData("Hello World Live Data")
    val liveData: LiveData<String> = _livedata

    private val _stateFlow = MutableStateFlow("Hello World State Flow")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>(replay = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun triggerLiveData() {
        _livedata.value = "LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            emit("Hello World Flow")
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }
}