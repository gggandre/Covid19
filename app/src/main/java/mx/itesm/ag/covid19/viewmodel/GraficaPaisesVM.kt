package mx.itesm.ag.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.ag.covid19.model.Pais
import mx.itesm.ag.covid19.model.PaisDatos
import mx.itesm.ag.covid19.model.ServicioCovidAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GraficaPaisesVM : ViewModel() {
    val pais = MutableLiveData<Pais>()
    val paisDatos = MutableLiveData<PaisDatos>()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://disease.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val covidApiService by lazy {
        retrofit.create(ServicioCovidAPI::class.java)
    }
    fun obtenerDatosCovid(countryName: String) {
        val call = covidApiService.obtenerDatosCovid(countryName)
        call.enqueue(object: Callback<Pais> {
            override fun onResponse(call: Call<Pais>, response: Response<Pais>) {
                if (response.isSuccessful) {
                    pais.value = response.body()
                } else {
                    println("Error en los datos {$response.body()}")
                }

            }

            override fun onFailure(call: Call<Pais>, t: Throwable) {
                println("Error en los datos:")
            }
        })
    }
    fun obtenerDatosActuales(nombrePais: String, ultimosdias: Int) {
        val call = covidApiService.obtenerDatosActuales(nombrePais, ultimosdias)
        call.enqueue(object: Callback<PaisDatos> {
            override fun onResponse(call: Call<PaisDatos>, response: Response<PaisDatos>) {
                if (response.isSuccessful) {
                    paisDatos.value = response.body()
                } else {
                    println("Error en los datos {$response.body()}")
                }
            }
            override fun onFailure(call: Call<PaisDatos>, t: Throwable) {
                println("No es posible otener los datos")
            }
        })
    }
}