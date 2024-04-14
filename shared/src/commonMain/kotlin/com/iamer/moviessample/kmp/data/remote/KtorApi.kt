package com.iamer.moviessample.kmp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL ="https://api.themoviedb.org"
private const val API_KEY ="285dcedfc17a8c1cc87ff6b166092aec"

internal abstract class KtorApi {
    val client = HttpClient{

        //Timeout plugin to set up timeout milliseconds for client
        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }


        //Logging plugin combined with kermit(KMP Logger library)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
            logger = object: Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.w("response") { message }
                }
            }
        }

        //We can configure the BASE_URL and also
        //the deafult headers by defaultRequest builder
        defaultRequest {
            header("Content-Type", "application/json")
            //header("Authorization", "Bearer ${BuildKonfig.OPENAI_API_KEY}")
            url(BASE_URL)
        }

        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                useAlternativeNames = false
            })
        }
    }


    fun HttpRequestBuilder.pathUrl(path:String){
        url {
            takeFrom(BASE_URL)
            path("3",path)
            parameter("api_key", API_KEY)
        }
    }
}