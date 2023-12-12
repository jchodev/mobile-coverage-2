package com.jerry.assessment.base.presentation

/*
    common UI state
 */
sealed class UiState<out T> where T : Any? {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()

    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val errorAny: Any) : UiState<Nothing>()

}
