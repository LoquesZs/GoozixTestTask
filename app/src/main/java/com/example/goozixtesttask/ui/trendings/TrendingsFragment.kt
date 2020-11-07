package com.example.goozixtesttask.ui.trendings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.goozixtesttask.R
import com.example.goozixtesttask.databinding.TrendingsFragmentBinding
import com.example.goozixtesttask.utils.GifListAdapter

class TrendingsFragment : Fragment() {

    private val viewModel: TrendingsViewModel by lazy {
        ViewModelProvider(this).get(TrendingsViewModel::class.java)
    }
    private lateinit var binding: TrendingsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.trendings_fragment,
            container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.gifList.adapter = GifListAdapter()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.searchButton.setOnClickListener {
            Log.d("VMSQ", "${viewModel.models.value?.get(0)?.title}")
            viewModel.getGiphySearchModel()
        }
    }
}