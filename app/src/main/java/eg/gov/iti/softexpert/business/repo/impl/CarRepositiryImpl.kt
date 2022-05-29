package eg.gov.iti.softexpert.business.repo.impl

import eg.gov.iti.softexpert.business.dataSource.abstraction.CarDataSource
import eg.gov.iti.softexpert.business.repo.abstraction.CarReository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CarRepositiryImpl @Inject constructor(private val carDataSource: CarDataSource) :
    CarReository {


    override suspend fun getCars(pageNumber: Int) = flow {
        emit(carDataSource.getCars(pageNumber))
    }.flowOn(IO)

}