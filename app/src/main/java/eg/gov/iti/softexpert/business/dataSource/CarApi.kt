package eg.gov.iti.softexpert.business.dataSource

import eg.gov.iti.softexpert.business.entities.CarResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("api/v1/cars")
    suspend fun getCars(
        @Query("page") page: Int
    ): CarResponse

}