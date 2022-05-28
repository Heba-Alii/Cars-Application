package eg.gov.iti.softexpert.business.repo.abstraction

import eg.gov.iti.softexpert.business.entities.CarResponse
import kotlinx.coroutines.flow.Flow

interface CarReository {
    suspend fun getCars(pageNumber: Int): Flow<CarResponse>
}