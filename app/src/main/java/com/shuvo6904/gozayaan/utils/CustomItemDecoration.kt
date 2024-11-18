package com.shuvo6904.gozayaan.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(
    private val firstItemSpacing: Int,
    private val itemSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            // Apply spacing for the first item
            outRect.left = firstItemSpacing
            outRect.right = itemSpacing / 2
        } else {
            // Apply spacing for other items
            outRect.left = itemSpacing / 2
            outRect.right = itemSpacing / 2
        }
    }
}
