package hector.ruiz.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getPhotoUseCase: GetPhotoUseCase) : ViewModel() {
}
