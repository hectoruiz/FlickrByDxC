package hector.ruiz.data.repositories

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.usecase.repositories.SearchPhotoRepository
import javax.inject.Inject

class SearchPhotoRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    SearchPhotoRepository {

    override suspend fun searchPhoto(keyword: String) = networkDataSource.searchPhoto(keyword)
}
