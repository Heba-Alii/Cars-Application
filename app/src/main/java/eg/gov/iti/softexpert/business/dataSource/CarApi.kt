package eg.gov.iti.softexpert.business.dataSource

import eg.gov.iti.softexpert.business.entities.CarResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("http://demo1585915.mockable.io/api/v1/cars")
    suspend fun getCars(
        @Query("page") page: Int
    ): CarResponse

}