package com.path_studio.adil.ui.main.category

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.path_studio.adil.R
import com.path_studio.adil.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set Grid Layout & RecyclerView
        if(activity!= null){
            val category = dummyData()
            val categoryAdapter = CategoryAdapter()
            categoryAdapter.setCategories(category)

            with(binding.rvListCategory){
                layoutManager = GridLayoutManager(context,3,LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = categoryAdapter
            }

        }

    }

    //For Test
    class Icon(
        var mIcon : Int = 0,
        var mTitle : String  =""
    )

    //Dummy Data
    private fun dummyData() : ArrayList<Icon>{
        var items : ArrayList<Icon> = ArrayList()

        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))
        items.add(Icon(R.drawable.ic_keuangan,"Keuangan"))

        return items
    }

}