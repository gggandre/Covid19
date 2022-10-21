package mx.itesm.ag.covid19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.ag.covid19.model.Pais
import mx.itesm.ag.covid19.model.ServicioCovidAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaPaisesVM : ViewModel() {
    val paises = MutableLiveData<List<Pais>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://disease.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val covidApiService by lazy {
        retrofit.create(ServicioCovidAPI::class.java)
    }

    fun descargarDatosCovid() {
        val call = covidApiService.descargarDatosCovid()
        call.enqueue(object: Callback<List<Pais>> {
            override fun onResponse(call: Call<List<Pais>>, response: Response<List<Pais>>) {
                if (response.isSuccessful) {
                   paises.value = response.body()
                } else {
                    println("Error en los datos: {$response.body()}")

                }

            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                println("No fue posible obtener los datos")
            }
        })
    }
}