package eg.gov.iti.softexpert.business.useCase.impl

import eg.gov.iti.softexpert.business.repo.abstraction.CarReository
import eg.gov.iti.softexpert.business.useCase.abtraction.CarUseCase

class CarUseCaseImpl(private val carRepository: CarReository) : CarUseCase {

    override suspend fun getCars(pageNumber: Int) = carRepository.getCars(pageNumber)

}