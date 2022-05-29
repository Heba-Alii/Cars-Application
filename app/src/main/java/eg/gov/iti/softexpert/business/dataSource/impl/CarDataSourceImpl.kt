package eg.gov.iti.softexpert.business.dataSource.impl

import eg.gov.iti.softexpert.business.dataSource.CarApi
import eg.gov.iti.softexpert.business.dataSource.abstraction.CarDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi

class CarDataSourceImpl @Inject constructor(private val carApi: CarApi) : CarDataSource {

    override suspend fun getCars(pageNumber: Int) = carApi.getCars(pageNumber)
}
