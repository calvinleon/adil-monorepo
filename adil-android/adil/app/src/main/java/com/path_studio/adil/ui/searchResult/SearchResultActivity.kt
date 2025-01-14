package com.path_studio.adil.ui.searchResult

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.functions.FirebaseFunctions
import com.google.gson.Gson
import com.path_studio.adil.data.source.remote.response.QueryHitItem
import com.path_studio.adil.data.source.remote.response.QueryResponse
import com.path_studio.adil.databinding.ActivitySearchResultBinding
import com.path_studio.adil.viewModel.ViewModelFactory

class SearchResultActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    private lateinit var binding: ActivitySearchResultBinding
    private var functions = FirebaseFunctions.getInstance("asia-southeast2")

    companion object{
        const val EXTRA_QUERY = "extra_query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[SearchResultViewModel::class.java]
        val rvSearchAdapter = SearchResultAdapter(this)

        val extras = intent.extras
        if(extras != null) {
            binding.progressBar.visibility = View.VISIBLE
            val query = extras.getString(EXTRA_QUERY)

            viewModel.queryLegislation(query.toString()).addOnCompleteListener {
                val hitItems = it.result

                val jumlahPeraturan = "Total ${hitItems?.size} jumlah peraturan"
                binding.textView.text = jumlahPeraturan

                binding.progressBar.visibility = View.GONE

                rvSearchAdapter.setLegislation(hitItems)
                rvSearchAdapter.notifyDataSetChanged()

                with(binding.rvListNotification){
                    binding.rvListNotification.layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = rvSearchAdapter
                }
            }
        }

        binding.searchLegis.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {

                viewModel.queryLegislation(query).addOnCompleteListener {
                    val hitItems = it.result

                    val jumlahPeraturan = "Total ${hitItems?.size} jumlah peraturan"
                    binding.textView.text = jumlahPeraturan

                    rvSearchAdapter.setLegislation(hitItems)
                    rvSearchAdapter.notifyDataSetChanged()

                    with(binding.rvListNotification) {
                        binding.rvListNotification.layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = rvSearchAdapter
                    }
                }

                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        // Set back button listener
        binding.backButton.setOnClickListener {
            finish()
        }

    }

}