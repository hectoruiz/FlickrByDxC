package hector.ruiz.data.repositories

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.usecase.repositories.GetSizesPhotoRepository
import javax.inject.Inject

class GetSizesPhotoRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    GetSizesPhotoRepository {

    override suspend fun getSizesPhoto(idPhoto: String) =
        networkDataSource.getSizesPhoto(idPhoto)
}
