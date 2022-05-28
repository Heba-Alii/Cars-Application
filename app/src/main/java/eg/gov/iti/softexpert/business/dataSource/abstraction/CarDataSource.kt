package eg.gov.iti.softexpert.business.dataSource.abstraction

import eg.gov.iti.softexpert.business.entities.CarResponse

interface CarDataSource {
    suspend fun getCars(pageNumber: Int): CarResponse
}