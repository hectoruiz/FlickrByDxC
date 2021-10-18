package hector.ruiz.usecase.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.photo.info.PhotoResponse
import hector.ruiz.usecase.repositories.GetInfoPhotoRepository
import hector.ruiz.usecase.repositories.SearchPhotoRepository
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val searchPhotoRepository: SearchPhotoRepository,
    private val getInfoPhotoRepository: GetInfoPhotoRepository
) {

    suspend operator fun invoke(keyword: String): MutableList<ResponseResult<PhotoResponse>> {
        val searchResponse = searchPhotoRepository.searchPhoto(keyword)

        val photoResponseList: MutableList<ResponseResult<PhotoResponse>> = mutableListOf()
        searchResponse.data?.photos?.photo?.forEach {
            val photo = getInfoPhotoRepository.getInfoPhoto(it?.id.orEmpty(), it?.secret.orEmpty())
            photoResponseList.add(photo)
        }
        return photoResponseList
    }
}
