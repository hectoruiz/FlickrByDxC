package hector.ruiz.usecase.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import hector.ruiz.usecase.repositories.SearchPhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val searchPhotoRepository: SearchPhotoRepository,
    private val getInfoPhotoRepository: GetInfoPhotoRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(keyword: String): MutableList<ResponseResult<PhotoResponse>> {
        val searchResponse = searchPhotoRepository.searchPhoto(keyword)

        val photoResponseList: MutableList<ResponseResult<PhotoResponse>> = mutableListOf()
        searchResponse.data?.photos?.photo?.mapNotNull {
            CoroutineScope(dispatcher).async {
                photoResponseList.add(
                    getInfoPhotoRepository.getInfoPhoto(
                        it?.id.orEmpty(),
                        it?.secret.orEmpty()
                    )
                )
            }
        }?.awaitAll()
        return photoResponseList
    }
}
