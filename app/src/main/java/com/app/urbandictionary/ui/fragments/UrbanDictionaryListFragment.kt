package com.app.urbandictionary.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.app.urbandictionary.R
import com.app.urbandictionary.adapter.UrbanDictionaryDataAdapter
import com.app.urbandictionary.model.UrbanDictionaryListModel
import com.app.urbandictionary.utils.UrbanDictionaryProgressDialog
import com.app.urbandictionary.viewmodel.UrbanDictionaryViewModel
import java.util.*
import kotlin.collections.ArrayList

class UrbanDictionaryListFragment : Fragment() {

    @BindView(R.id.urban_dictionary_list_recycler_view)
    lateinit var urbanDictionaryListRecyclerView: RecyclerView

    @BindView(R.id.empty_view)
    lateinit var emptyView: TextView
    var urbanDictionaryViewModel: UrbanDictionaryViewModel? = null
    var mAdapter: UrbanDictionaryDataAdapter? = null
    var searchTerm: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRetainInstance(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =
            inflater.inflate(R.layout.fragment_urban_dictionary_list, container, false)
        ButterKnife.bind(this, view)
        urbanDictionaryViewModel =
            ViewModelProviders.of(this).get(UrbanDictionaryViewModel::class.java)
        val arguments: Bundle? = getArguments()
        if (arguments?.getString("term") != null) searchTerm =
            arguments.getString("term")
        initRecyclerView()
        urbanDictionaryViewModel!!.init(searchTerm)
         UrbanDictionaryProgressDialog.show(this.context!!)
        urbanDictionaryViewModel?.urbanDictionaryResults?.observe(
            this,
            androidx.lifecycle.Observer {
                if (it != null) {
                    if (it.list?.size!! > 0) {
                        mAdapter?.setUrbanDictionaryListModels(it.list)
                        mAdapter?.notifyDataSetChanged()
                          UrbanDictionaryProgressDialog.dismissDialog()
                        emptyView.visibility = View.GONE
                        urbanDictionaryListRecyclerView.setVisibility(View.VISIBLE)
                    } else {
                         UrbanDictionaryProgressDialog.dismissDialog()
                        emptyView.visibility = View.VISIBLE
                        urbanDictionaryListRecyclerView.setVisibility(View.GONE)
                    }
                }

            })
        return view
    }

    private fun initRecyclerView() {
        mAdapter = UrbanDictionaryDataAdapter(ArrayList<UrbanDictionaryListModel>())
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getActivity())
        urbanDictionaryListRecyclerView.setLayoutManager(layoutManager)
        urbanDictionaryListRecyclerView.setAdapter(mAdapter)
    }

    override fun onStart() {
        super.onStart()
    }

    /**
     * This method sorts the the urban list by thumbs up or thumbs down count.
     * @param thumbsCountSelection thumbs count
     */
    fun sortByThumbsCount(thumbsCountSelection: Boolean) {
        var urbanDictionaryListModel: ArrayList<UrbanDictionaryListModel>? =
            mAdapter?.urbanDictonaryModel
        if (thumbsCountSelection) {
            urbanDictionaryListModel?.sortBy { it.thumbs_up }
        } else {
            urbanDictionaryListModel?.sortBy { it.thumbs_down }
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {
        fun newInstance(): UrbanDictionaryListFragment {
            return UrbanDictionaryListFragment()
        }
    }
}
