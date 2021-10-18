package hector.ruiz.flickrbydxc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.data.repositories.GetInfoPhotoRepositoryImpl
import hector.ruiz.data.repositories.GetSizesPhotoRepositoryImpl
import hector.ruiz.data.repositories.SearchPhotoRepositoryImpl
import hector.ruiz.datasource.datasources.NetworkDataSourceImpl
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import hector.ruiz.usecase.repositories.GetSizesPhotoRepository
import hector.ruiz.usecase.repositories.SearchPhotoRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource

    @Binds
    abstract fun bindsSearchPhotoRepository(searchPhotoRepositoryImpl: SearchPhotoRepositoryImpl): SearchPhotoRepository

    @Binds
    abstract fun bindsGetInfoPhotoRepository(getInfoPhotoRepositoryImpl: GetInfoPhotoRepositoryImpl): GetInfoPhotoRepository

    @Binds
    abstract fun bindsGetSizesPhotoRepository(getSizesPhotoRepositoryImpl: GetSizesPhotoRepositoryImpl): GetSizesPhotoRepository
}
