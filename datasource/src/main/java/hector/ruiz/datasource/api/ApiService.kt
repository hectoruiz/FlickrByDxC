package hector.ruiz.datasource.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET()
    suspend fun getService(): Response<Any>
}
