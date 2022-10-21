package mx.itesm.ag.covid19.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.itesm.ag.covid19.databinding.ActivityMainBinding
import mx.itesm.ag.covid19.view.AdaptadorPais
import mx.itesm.ag.covid19.viewmodel.ListaPaisesVM

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListaPaisesVM by viewModels()
    var adaptadorPais: AdaptadorPais? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()
        viewModel.descargarDatosCovid()
    }
}