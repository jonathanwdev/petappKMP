package com.pet.app.utils

sealed class NetworkException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class ApiException(val responseMessage: String, val statusCode: Int) : NetworkException(responseMessage)

    class UnknownNetworkException(cause: Throwable? = null) : NetworkException("An unknown error occurred", cause)
}