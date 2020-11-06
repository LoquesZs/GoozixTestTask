package com.example.goozixtesttask.ui.trendings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goozixtesttask.R

class TrendingsFragment : Fragment() {

    companion object {
        fun newInstance() = TrendingsFragment()
    }

    private lateinit var viewModel: TrendingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}