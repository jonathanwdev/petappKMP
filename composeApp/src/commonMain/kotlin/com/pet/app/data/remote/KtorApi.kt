package com.pet.app.data.remote

import com.pet.app.data.remote.models.TokenResponse
import com.pet.app.utils.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json


class InitializeKtor {
    lateinit var client: HttpClient
    private var accessToken: String = ""

    init {
        startKtor()
    }

    companion object {
        const val BASE_URL = "https://api.petfinder.com/v2/"
        const val API_KEY = "R3ZxUpkQiC3l823bxxA4l08TsB40O81XkHrB2O2Lf5mOh4a4BE"
        const val API_SECRET = "4eNWrJR1vTzzgRdybjzNfYAo3UXDmeY5lwDQ7WrP"
    }


    private suspend fun refreshAccessToken(): TokenResponse {
        val response = client.post("oauth2/token") {
            contentType(ContentType.Application.FormUrlEncoded)
            setBody(
                "grant_type=client_credentials&" +
                        "client_id=$API_KEY&" +
                        "client_secret=$API_SECRET"
            )
        }
        return response.body<TokenResponse>()
    }

    private fun startKtor(): HttpClient {
        client = HttpClient(CIO) {
            expectSuccess = true
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken,
                            ""
                        )
                    }
                    refreshTokens {
                        val newTokens = refreshAccessToken()
                        accessToken = newTokens.accessToken
                        BearerTokens(newTokens.accessToken, "")
                    }

                }
            }

            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    throw if (cause is ClientRequestException) {
                        val errorMessage = cause.response.bodyAsText()
                        NetworkException.ApiException(errorMessage, cause.response.status.value)
                    } else {
                        NetworkException.UnknownNetworkException(cause)
                    }
                }
            }
        }
        return client
    }
}

