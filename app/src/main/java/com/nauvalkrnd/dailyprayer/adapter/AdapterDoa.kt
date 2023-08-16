package com.nauvalkrnd.dailyprayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nauvalkrnd.dailyprayer.R
import com.nauvalkrnd.dailyprayer.model.ModelDoa
import com.nauvalkrnd.dailyprayer.adapter.AdapterDoa.ListViewHolder
import kotlinx.android.synthetic.main.list_item_doa.view.tvArabic
import kotlinx.android.synthetic.main.list_item_doa.view.tvId
import kotlinx.android.synthetic.main.list_item_doa.view.tvLatin
import kotlinx.android.synthetic.main.list_item_doa.view.tvTerjemahan
import kotlinx.android.synthetic.main.list_item_doa.view.tvTitle
import java.util.Locale

class AdapterDoa(private val modelBacaan: MutableList<ModelDoa>) :
    RecyclerView.Adapter<ListViewHolder>(), Filterable {

   var modelBacaanListFull: List<ModelDoa>

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList: MutableList<ModelDoa> = ArrayList()
                if (constraint == null || constraint.length == 0) {
                    filteredList.addAll(modelBacaanListFull)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT)
                    for (modelDoaFilter in modelBacaanListFull) {
                        if (modelDoaFilter.strTitle!!.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(modelDoaFilter)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                modelBacaan.clear()
                modelBacaan.addAll(results.values as List<ModelDoa>)
                notifyDataSetChanged()

                }
            }

        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_doa, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(listViewHolder: ListViewHolder, i: Int) {
        val dataModel = modelBacaan[i]
        listViewHolder.tvId.text = dataModel.strId
        listViewHolder.tvTitle.text = dataModel.strTitle
        listViewHolder.tvArabic.text = dataModel.strArabic
        listViewHolder.tvLatin.text = dataModel.strLatin
        listViewHolder.tvTerjemahan.text = dataModel.strTranslation
    }

    override fun getItemCount(): Int {
        return modelBacaan.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvId: TextView
        var tvTitle: TextView
        var tvArabic: TextView
        var tvLatin: TextView
        var tvTerjemahan: TextView

        init {
            tvId = itemView.tvId
            tvTitle = itemView.tvTitle
            tvArabic = itemView.tvArabic
            tvLatin = itemView.tvLatin
            tvTerjemahan = itemView.tvTerjemahan
        }
    }

    init {
        modelBacaanListFull = ArrayList(modelBacaan)
    }

}