package com.app.urbandictionary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.app.urbandictionary.R
import com.app.urbandictionary.model.UrbanDictionaryListModel

class UrbanDictionaryDataAdapter(urbanDictionaryListModel: ArrayList<UrbanDictionaryListModel>) :
    RecyclerView.Adapter<UrbanDictionaryDataAdapter.ViewHolder?>() {

    private var urbanDictionaryListModels: ArrayList<UrbanDictionaryListModel>

    @NonNull
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.urban_dictionary_recycler_row, parent, false)
        return ViewHolder(view)
    }

    fun setUrbanDictionaryListModels(urbanDictionaryListModel: ArrayList<UrbanDictionaryListModel>) {
        urbanDictionaryListModels = urbanDictionaryListModel
    }


    val urbanDictonaryModel: ArrayList<UrbanDictionaryListModel>
        get() = urbanDictionaryListModels

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val urbanDictionaryListModel: UrbanDictionaryListModel =
            urbanDictionaryListModels.get(position)
        holder.definition?.setText(urbanDictionaryListModel.definition)
        holder.thumbsup?.setText(urbanDictionaryListModel.thumbs_up.toString())
        holder.thumbsdown?.setText(urbanDictionaryListModel.thumbs_down.toString())
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        @BindView(R.id.defnition)
        lateinit var definition: TextView

        @BindView(R.id.thumbsup)
        lateinit var thumbsup: TextView

        @BindView(R.id.thumbsdown)
        lateinit var thumbsdown: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    init {
        urbanDictionaryListModels = urbanDictionaryListModel
    }

    override fun getItemCount(): Int {
        return urbanDictionaryListModels.size
    }

}
