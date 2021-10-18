package hector.ruiz.flickrbydxc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.flickrbydxc.databinding.ActivityMainBinding
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var useCase: GetPhotoUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

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
