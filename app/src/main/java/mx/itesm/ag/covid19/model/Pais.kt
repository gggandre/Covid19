package mx.itesm.ag.covid19.model

import com.google.gson.annotations.SerializedName

/**
 * @author Gilberto André García Gaytán
 * Class to represent one country.
 */
data class Pais(
    @SerializedName("country")
    val name: String,
    @SerializedName("cases")
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("countryInfo")
    val info: Map<String, String> = mapOf(),
    val imageId: Int = 0,
    val imageUrl: String = "")
