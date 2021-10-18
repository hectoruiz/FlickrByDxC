package hector.ruiz.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.usecase.usecases.GetPhotoUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getPhotoUseCase: GetPhotoUseCase) :
    ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        manageError()
    }

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _photoList: MutableLiveData<MutableList<ResponseResult<PhotoResponse>>> =
        MutableLiveData()
    val photoList: LiveData<MutableList<ResponseResult<PhotoResponse>>>
        get() = _photoList

    private val _errorRequest: MutableLiveData<Boolean> = MutableLiveData()
    val errorRequest: LiveData<Boolean>
        get() = _errorRequest

    fun searchPhotos(keyword: String) =
        viewModelScope.launch(exceptionHandler) {
            _isLoading.postValue(true)
            val result = getPhotoUseCase(keyword)
            if (result.isNotEmpty()) {
                _photoList.postValue(result)
                _isLoading.postValue(false)
            } else {
                manageError()
            }
        }

    private fun manageError() {
        _isLoading.postValue(false)
        if (_photoList.value.isNullOrEmpty()) {
            _errorRequest.postValue(true)
        }
    }
}
