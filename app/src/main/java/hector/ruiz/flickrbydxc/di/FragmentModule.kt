package hector.ruiz.flickrbydxc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hector.ruiz.flickrbydxc.ui.list.PhotoAdapter

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun photoAdapterProvider(): PhotoAdapter {
        return PhotoAdapter()
    }
}
