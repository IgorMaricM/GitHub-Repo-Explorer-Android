package com.im.githubrepoexplorer.networking

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    //explicitNulls = false
                    encodeDefaults = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10_000
                connectTimeoutMillis = 10_000
                socketTimeoutMillis = 10_000
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("KtorClient").d(message)
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    host = API_BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideKtorClientWrapper(client: HttpClient): KtorClient {
        return KtorClient(client)
    }

    private const val API_BASE_URL = "api.github.com"
}

class KtorClient @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getAllRepos(query: String, pageNum: Int, paginationSize: Int = 20): AllReposResponse? {
        return try {
            return client.get(REPOS) {
                parameter(
                    "q", if (query.isBlank()) {
                        "language:kotlin"
                    } else {
                        "language:kotlin $query in:name,description"
                    }
                )
                parameter("order", "desc")
                parameter("sort", "stars")
                parameter("per_page", paginationSize)
                parameter("page", pageNum)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    suspend fun getRepoDetails(owner: String): RepoModel? {
        return try {
            return client.get(REPO_DETAIL + owner) {
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    suspend fun getContributors(url: String): List<ContributorModel> {
        return try {
            return client.get(url) {
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }
    }

    companion object {
        const val PAGINATION_SIZE = 20

        private const val REPOS = "search/repositories?"
        private const val REPO_DETAIL = "repos/"
    }

}


