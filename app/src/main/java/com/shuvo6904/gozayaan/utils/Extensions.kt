package com.shuvo6904.gozayaan.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {
    fun ImageView.loadImage(url: String?) {
        Glide.with(context)
            .load(url)
            .skipMemoryCache(false)
            .into(this)
    }
}