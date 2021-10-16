package hector.ruiz.usecase.usecases

import hector.ruiz.usecase.repositories.Repository
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): Any {
        return repository.methodRepository()
    }
}
