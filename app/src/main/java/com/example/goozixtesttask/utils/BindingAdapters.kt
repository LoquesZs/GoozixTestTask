package com.example.goozixtesttask.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
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

    /**
     * We need WindowManager and display size because the use of Glide's placeholder deals with bug
     * that makes downloaded Gif image's size fit the size of placeholder image.
     **/

    val windowManager = imageView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    val placeholderWidth = size.x
    val placeholderHeight = size.y
    original?.url?.let {
        val imageUri = original.url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .override(placeholderWidth, placeholderHeight)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, dataList: List<Data>?) {
    val adapter = recyclerView.adapter as GifListAdapter
    adapter.submitList(dataList)
}