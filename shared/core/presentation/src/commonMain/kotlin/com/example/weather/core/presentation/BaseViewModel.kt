package com.example.weather.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.core.domain.DispatcherProvider
import com.example.weather.core.domain.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S>(
    initialState: S,
    protected val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<S>>(UiState.Success(initialState))
    val uiState: StateFlow<UiState<S>> = _uiState.asStateFlow()

    protected fun setLoading() {
        _uiState.value = UiState.Loading
    }

    protected fun setSuccess(data: S) {
        _uiState.value = UiState.Success(data)
    }

    protected fun setError(message: String) {
        _uiState.value = UiState.Error(message)
    }

    protected fun <T> launchWithResult(
        block: suspend () -> Result<T>,
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit = { setError(it) }
    ) {
        viewModelScope.launch(dispatcherProvider.io) {
            setLoading()
            when (val result = block()) {
                is Result.Success -> onSuccess(result.data)
                is Result.Error -> onError(result.error.message)
            }
        }
    }
}
