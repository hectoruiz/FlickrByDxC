package hector.ruiz.datasource.datasources

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.datasource.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(retrofit: Retrofit) : NetworkDataSource {

    private val service = retrofit.create<ApiService>()

    override suspend fun methodNetworkDataSource() : Any {
        return withContext(Dispatchers.IO) {
            service.getService().let {
                if (it.isSuccessful) {
                    Any()
                } else {
                    Any()
                }
            }
        }
    }
}
