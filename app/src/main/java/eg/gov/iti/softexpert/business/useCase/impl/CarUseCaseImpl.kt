package eg.gov.iti.softexpert.business.useCase.impl

import eg.gov.iti.softexpert.business.repo.abstraction.CarReository
import eg.gov.iti.softexpert.business.useCase.abtraction.CarUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi

class CarUseCaseImpl @Inject constructor(private val carRepository: CarReository) : CarUseCase {

    override suspend fun getCars(pageNumber: Int) = carRepository.getCars(pageNumber)

}