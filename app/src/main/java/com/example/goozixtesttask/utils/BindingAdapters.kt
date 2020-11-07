package com.example.goozixtesttask.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goozixtesttask.R
import com.example.goozixtesttask.network.Data
import com.example.goozixtesttask.network.Original

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, original: Original?) {
    original?.url?.let {
        val imageUri = original.url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
            )
            .override(original.width, original.height)
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, dataList: List<Data>?) {
    val adapter = recyclerView.adapter as GifListAdapter
    adapter.submitList(dataList)
}