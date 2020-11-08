package com.example.goozixtesttask.ui.trendings

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.goozixtesttask.R
import com.example.goozixtesttask.databinding.TrendingsFragmentBinding
import com.example.goozixtesttask.utils.GifListAdapter
import com.example.goozixtesttask.utils.MarginItemDecoration
import com.example.goozixtesttask.utils.OnSwipeListener
import com.omega_r.libs.omegaintentbuilder.OmegaIntentBuilder
import com.omega_r.libs.omegaintentbuilder.downloader.DownloadCallback
import com.omega_r.libs.omegaintentbuilder.handlers.ContextIntentHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrendingsFragment : Fragment() {

    private val viewModel: TrendingsViewModel by lazy {
        ViewModelProvider(this).get(TrendingsViewModel::class.java)
    }
    private lateinit var binding: TrendingsFragmentBinding

    private var isSharing = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.trendings_fragment,
            container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.gifList.adapter = GifListAdapter(GifListAdapter.OnClickListener{
        /**
         * Taken here: https://stackoverflow.com/a/47983377
         * **/
            if (context != null && !isSharing) {
                Toast.makeText(context!!, "Wait...", Toast.LENGTH_LONG).show()
                GlobalScope.launch(Dispatchers.Main) {
                    binding.shareProgress.visibility = View.VISIBLE
                    isSharing = true
                    OmegaIntentBuilder(context!!)
                        .share()
                        .filesUrls(it.images.original.url)
                        .download(object: DownloadCallback {
                            override fun onDownloaded(success:Boolean, contextIntentHandler: ContextIntentHandler) {
                                binding.shareProgress.visibility = View.GONE
                                contextIntentHandler.startActivity()
                                isSharing = false
                            }
                        })
                }
            }
        })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.searchButton.setOnClickListener {
            viewModel.getGiphySearchModel()
            hideKeyboard()
        }

        binding.gifList.addItemDecoration(
            MarginItemDecoration(
                resources.getDimension(R.dimen.default_margin).toInt())
        )

        binding.gifList.setOnTouchListener( object : OnSwipeListener(context) {
            override fun onSwipeBottom() {
                viewModel.getGiphyTrendingModel()
            }
        }
        )
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}