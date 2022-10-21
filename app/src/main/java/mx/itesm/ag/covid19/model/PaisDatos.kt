package mx.itesm.ag.covid19.model

import com.google.gson.annotations.SerializedName

data class PaisDatos(
    @SerializedName("country")
    val name: String,
    val timeline: Map<String, Map<String, String>> = mapOf(),
)
