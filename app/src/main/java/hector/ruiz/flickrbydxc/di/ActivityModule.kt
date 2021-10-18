package hector.ruiz.flickrbydxc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import hector.ruiz.usecase.repositories.SearchPhotoRepository
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun getPhotoUseCaseProvider(
        searchPhotoRepository: SearchPhotoRepository,
        getInfoPhotoRepository: GetInfoPhotoRepository,
        coroutineDispatcher: CoroutineDispatcher
    ) = GetPhotoUseCase(searchPhotoRepository, getInfoPhotoRepository, coroutineDispatcher)
}
