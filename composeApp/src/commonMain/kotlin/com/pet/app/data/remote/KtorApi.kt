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
                        BearerTokens("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJSM1p4VXBrUWlDM2w4MjNieHhBNGwwOFRzQjQwTzgxWGtIckIyTzJMZjVtT2g0YTRCRSIsImp0aSI6IjBjYmQwZWUwOGZmNzdlMjQwOTQzMGU5MGMyYTdlYTkyN2Q1NTJkYzUyMWEyZDRiNmM5NGVjZTA3N2JiNDcyZTIwOWIwMDAyMDQ2OTk0ZjdiIiwiaWF0IjoxNzMzMzU4ODU4LCJuYmYiOjE3MzMzNTg4NTgsImV4cCI6MTczMzM2MjQ1OCwic3ViIjoiIiwic2NvcGVzIjpbXX0.a2HeW2vwL-Fg7EzMwDf8K0fL5SPMJ2Wc62EJ7BdWQrBbdLfKPd3RbvpXNO4BeQDx1G4V5EnQ2EFnXNTLO7UjXJLX8oYw8UYHYqBc2kXL1eDXEia5FifInkZryyR9iB0-rthIZkYXYy7vlxofm77dFoxVpOt8ThlOd6TK5VOLTVjRkK13xoUz4bo32vCjriIFhfwmHfZ7B0bQjhzNJX-SfZgtbI0IZoZgZZQlX-Cpvw9l-AgCqF7MDzgYwjDMSg115hbw6wrBJ1vRgsooB9jPYzjSY-ow957VZapUGTuPKrxGxFKUEyw8MSC68_IhMt3p9M4DRLG2s0ySQBjKNkC_PA", "")
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

