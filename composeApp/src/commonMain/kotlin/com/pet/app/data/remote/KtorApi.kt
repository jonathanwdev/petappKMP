package com.pet.app.data.remote

import com.pet.app.utils.NetworkException
import io.ktor.client.HttpClient
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
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class InitializeKtor {
    lateinit var client: HttpClient

    init {
        startKtor()
    }

    companion object {
        const val BASE_URL = "https://api.petfinder.com/v2/"
        const val API_KEY = "R3ZxUpkQiC3l823bxxA4l08TsB40O81XkHrB2O2Lf5mOh4a4BE"
        const val API_SECRET = "4eNWrJR1vTzzgRdybjzNfYAo3UXDmeY5lwDQ7WrP"
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
                        BearerTokens("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJSM1p4VXBrUWlDM2w4MjNieHhBNGwwOFRzQjQwTzgxWGtIckIyTzJMZjVtT2g0YTRCRSIsImp0aSI6ImMyMzk2NjY4YmM5NTNmOWU3MDViNzE3ZDAwZTQyN2M1MjBmZjIzMGVjMzE4Y2M0MTdhOWQ3NzcxYzU4MTZlYjFmM2M0ZGU4MDVjYzI4MzNjIiwiaWF0IjoxNzMzNDQ4MTA4LCJuYmYiOjE3MzM0NDgxMDgsImV4cCI6MTczMzQ1MTcwOCwic3ViIjoiIiwic2NvcGVzIjpbXX0.II2QYEk7liD6Q7Si0tYzn9uAsmzR0mFUCaDkdGoxNpb0_nw44bkeb-PAL4ut13u4EMtFBurYafVvoJeUNyKECZ5MQI3BrosEZMp689tqw1BVgAeFebrvKzAo085s9qtPfzI7SPsxPkOQ8PKVQ4BiGAQBPevcpg52T54kc9zy-eGAObfnwX1HAhv4eZrkiB_J8tPpV8yZmvmhhkURGeiZ3hUrg3qgnq120eEejRgyHOHS0l73YzfwfRReKcRXms99Dfh5dL35TuLB8IFH7miVdeW8jg-2wwMViqrOvRQAYPIiOlcqwtdbqjW272L6ICv2uPqfJxFUmt7n1xkY1JBQUQ", "")
                    }

                }
            }

            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, request ->
                    throw if(cause is ClientRequestException) {
                        val errorMessage = cause.response.bodyAsText()
                        NetworkException.ApiException(errorMessage, cause.response.status.value)
                    }else {
                        NetworkException.UnknownNetworkException(cause)
                    }
                }
            }
        }
        return client
    }
}

