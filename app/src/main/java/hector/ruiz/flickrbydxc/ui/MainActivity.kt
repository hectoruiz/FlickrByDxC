package hector.ruiz.flickrbydxc.ui

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var useCase: GetPhotoUseCase

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            useCase("externocleidomastoideo").forEach {
                println(it.data?.photo?.id)
                println(it.data?.photo?.secret)
            }
        }
    }
}
