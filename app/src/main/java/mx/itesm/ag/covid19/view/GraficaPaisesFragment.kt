package mx.itesm.ag.covid19.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import mx.itesm.ag.covid19.R
import mx.itesm.ag.covid19.databinding.FragmentGraficaBinding
import mx.itesm.ag.covid19.viewmodel.GraficaPaisesVM


class GraficaPaisesFragment : Fragment() {

    private val args: GraficaPaisesFragmentArgs by navArgs()
    private val viewModel: GraficaPaisesVM by viewModels()
    private lateinit var binding: FragmentGraficaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGraficaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        DatosPaises()
        DatosCovidActuales()

        viewModel.obtenerDatosCovid(args.nombrePais)
        viewModel.obtenerDatosActuales(args.nombrePais, 800)
    }

    private fun DatosCovidActuales() {
        viewModel.paisDatos.observe(viewLifecycleOwner) { countryTimeSeriesData ->
            val data = ArrayList<Entry>()
            var idx = 0
            countryTimeSeriesData.timeline["cases"]?.forEach { dayCases ->
                data.add(Entry(idx.toFloat(), dayCases.value.toFloat()))
                idx += 1
            }
            val lineDataSet = LineDataSet(data, "Contagios")
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(lineDataSet)
            val lineData = LineData(dataSets)
            binding.lineChartView.data = lineData
            binding.lineChartView.invalidate()
            binding.lineChartView.description.text = "COVID-19"
        }
    }


    private fun DatosPaises() {
        viewModel.pais.observe(viewLifecycleOwner) { countryData ->
            binding.tvPaisActual.text = countryData.name
            val imgFlag = view?.findViewById<ImageView>(R.id.imgBandera)
            if (imgFlag != null) {
                Glide.with(requireContext()).load(countryData.info["flag"]).into(imgFlag)
            }
            binding.tvCasosActuales.text = countryData.cases.toString()
            binding.tvCasosRecuperdos.text = countryData.recovered.toString()
            binding.tvDecesos.text = countryData.deaths.toString()
        }
    }
}