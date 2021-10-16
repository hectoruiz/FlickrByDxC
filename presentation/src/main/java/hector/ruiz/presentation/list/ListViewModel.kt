package hector.ruiz.presentation.list

import androidx.lifecycle.ViewModel
import hector.ruiz.usecase.usecases.UseCase
import javax.inject.Inject

class ListViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {
}
