package mx.itesm.ag.covid19.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicioCovidAPI {
    @GET("v3/covid-19/countries")
    fun descargarDatosCovid(): Call<List<Pais>>

    @GET("v3/covid-19/countries/{country}")
    fun obtenerDatosCovid(@Path("country") countryName: String): Call<Pais>

    @GET("v3/covid-19/historical/{country}")
    fun obtenerDatosActuales(@Path("country") nombrePais: String,
                                  @Query("lastdays") ultimosdias: Int): Call<PaisDatos>
}