package com.app.urbandictionary.ui

import android.os.Bundle


import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import butterknife.ButterKnife
import com.app.urbandictionary.R
import com.app.urbandictionary.ui.fragments.UrbanDictionaryListFragment


class UrbanDictionaryActivity : AppCompatActivity() {
    var ab: ActionBar? = null
    var urbanDictionaryListFragment: UrbanDictionaryListFragment? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urban_dictionary_list)
        ab = getSupportActionBar()
        ab?.setDisplayShowTitleEnabled(true)
        ButterKnife.bind(this)
    }

    protected override fun onStart() {
        super.onStart()
    }

    fun showDictionaryFragment(term: String?) {
        val fm: FragmentManager = getSupportFragmentManager()
        val ft: FragmentTransaction = fm.beginTransaction()
        val termBundle = Bundle()
        termBundle.putString("term", term)
        urbanDictionaryListFragment = UrbanDictionaryListFragment.newInstance()
        urbanDictionaryListFragment!!.setArguments(termBundle)
        ft.replace(R.id.listcontainer, urbanDictionaryListFragment!!)
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.thumbs_up -> {
                urbanDictionaryListFragment?.sortByThumbsCount(
                    true
                )
            }
            R.id.thumbs_down -> {
                urbanDictionaryListFragment?.sortByThumbsCount(
                    false
                )
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        val search_item = menu.findItem(R.id.mi_search)
        val searchView: SearchView = search_item.actionView as SearchView
        searchView.setFocusable(false)
        searchView.setQueryHint("Search Urban Dictionary")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(term: String?): Boolean {
                ab?.setDisplayShowTitleEnabled(false)
                showDictionaryFragment(term)
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}