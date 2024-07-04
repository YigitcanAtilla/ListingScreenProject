package com.yigitcan.listingscreen.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val dividerHeight: Int) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = android.graphics.Color.GRAY
        strokeWidth = 1f
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + dividerHeight

            c.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = dividerHeight
    }
}