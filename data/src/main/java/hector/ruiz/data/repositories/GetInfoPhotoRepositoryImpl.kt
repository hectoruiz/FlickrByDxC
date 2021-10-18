package hector.ruiz.data.repositories

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import javax.inject.Inject

class GetInfoPhotoRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    GetInfoPhotoRepository {

    override suspend fun getInfoPhoto(idPhoto: String, secret: String) =
        networkDataSource.getInfoPhoto(idPhoto, secret)
}