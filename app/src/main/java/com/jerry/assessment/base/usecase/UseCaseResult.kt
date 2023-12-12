package com.jerry.assessment.base.usecase

sealed class UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>()
    data class CustomerError(val error: String): UseCaseResult<Nothing>()
    data class Failure(val throwable: Throwable) : UseCaseResult<Nothing>()
}