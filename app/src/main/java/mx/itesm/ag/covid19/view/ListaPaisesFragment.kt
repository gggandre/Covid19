package mx.itesm.ag.covid19.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.ag.covid19.R
import mx.itesm.ag.covid19.databinding.FragmentRenglonPaisBinding
import mx.itesm.ag.covid19.model.Pais
import mx.itesm.ag.covid19.viewmodel.ListaPaisesVM


class ListaPaisesFragment : Fragment() {

    private lateinit var binding: FragmentRenglonPaisBinding
    private val viewModel: ListaPaisesVM by viewModels()
    private var AdaptadorPais: AdaptadorPais? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRenglonPaisBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        Observables()
        RecyclerView()
        viewModel.descargarDatosCovid()
    }
    private fun Observables() {
        viewModel.paises.observe(this) { countryList ->
            AdaptadorPais?.countries = countryList.toTypedArray()
            AdaptadorPais?.notifyDataSetChanged()
        }
    }
    private fun RecyclerView() {
        val countries = arrayOf(Pais("México", 0, 0, 0))
        val layout = LinearLayoutManager(requireContext())
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.listaPaises.layoutManager = layout
        AdaptadorPais = AdaptadorPais(requireContext(), countries)
        AdaptadorPais?.setOnClickListener { p0 ->
            val tvCountryName: TextView? = p0?.findViewById(R.id.tvCountryName)
            if (tvCountryName != null) {
                val countryName = tvCountryName.text as String
                val action = ListaPaisesFragmentDirections
                    .actionCountryListFragmentToCountryGraphFragment(countryName)
                findNavController().navigate(action)
            }
        }
        binding.listaPaises.adapter = AdaptadorPais
        val divider = DividerItemDecoration(requireContext(), layout.orientation)
        binding.listaPaises.addItemDecoration(divider)
    }
}