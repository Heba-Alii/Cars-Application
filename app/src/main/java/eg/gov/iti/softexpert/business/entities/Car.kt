package eg.gov.iti.softexpert.business.entities

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id") val id: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("constractionYear") val constractionYear: String,
    @SerializedName("isUsed") val isUsed: Boolean


)