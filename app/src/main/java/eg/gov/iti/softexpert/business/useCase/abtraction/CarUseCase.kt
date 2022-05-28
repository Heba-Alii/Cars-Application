package eg.gov.iti.softexpert.business.useCase.abtraction

import eg.gov.iti.softexpert.business.entities.CarResponse
import kotlinx.coroutines.flow.Flow


interface CarUseCase {
    suspend fun getCars(pageNumber: Int): Flow<CarResponse>

}
