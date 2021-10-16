package hector.ruiz.data.repositories

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.usecase.repositories.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    Repository {

    override suspend fun methodRepository() =
        networkDataSource.methodNetworkDataSource()
}
