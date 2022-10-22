package mx.itesm.ag.covid19.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.itesm.ag.covid19.R
import mx.itesm.ag.covid19.model.Pais

/**
 * @author Gilberto AndreGarcía Gaytán
 * Los datos de
 * el recycler view como se vio en clase
 */
class AdaptadorPais(private val context: Context, var paises: Array<Pais>) : RecyclerView.Adapter<AdaptadorPais.Fila>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Fila { // se llama cada que la lista
        //se despliega
        val view = LayoutInflater.from(context).inflate(R.layout.renglon_pais, parent, false)
        view.setOnClickListener(this)
        return Fila(view)
    }
    override fun onBindViewHolder(holder: Fila, position: Int) {
        val pais =paises[position]
        holder.set(pais) // Pone la fila en su posicion
    }
    override fun getItemCount(): Int {
        return paises.size //recyclerview
    }
    class Fila (var countryRow: View) : RecyclerView.ViewHolder(countryRow) {
        fun set(country: Pais) {
            countryRow.findViewById<TextView>(R.id.tvCountryName).text = country.name
            countryRow.findViewById<TextView>(R.id.tvCases).text = "${country.cases}"
            val imgFlag = countryRow.findViewById<ImageView>(R.id.imgFlag)
            Glide.with(countryRow.context).load(country.info["flag"]).into(imgFlag)
        }
    }
    override fun onClick(view: View) {
        listener?.onClick(view)
    }
    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }
}