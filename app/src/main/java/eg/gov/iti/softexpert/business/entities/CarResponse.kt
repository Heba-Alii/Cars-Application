package eg.gov.iti.softexpert.business.entities

import com.google.gson.annotations.SerializedName

data class CarResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("data") val data: List<Car>
)
