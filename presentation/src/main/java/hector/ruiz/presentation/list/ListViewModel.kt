package hector.ruiz.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getPhotoUseCase: GetPhotoUseCase) : ViewModel() {

}
