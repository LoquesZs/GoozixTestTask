package com.example.goozixtesttask.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Taken here:
 * https://medium.com/mobile-app-development-publication/right-way-of-setting-margin-on-recycler-views-cell-319da259b641
 **/

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            left =  spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}