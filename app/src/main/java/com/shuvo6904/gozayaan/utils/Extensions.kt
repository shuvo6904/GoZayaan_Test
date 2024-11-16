package com.shuvo6904.gozayaan.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.widget.ImageView
import com.bumptech.glide.Glide

object Extensions {
    fun ImageView.loadImage(url: String?) {
        Glide.with(context)
            .load(url)
            .skipMemoryCache(false)
            .into(this)
    }

    inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(name: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelableExtra(name, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            getParcelableExtra(name) as? T
        }
    }

    inline fun <reified T> Context.openActivity(extras: Intent.() -> Unit = {}) {
        val intent = Intent(this, T::class.java)
        intent.extras()
        startActivity(intent)
    }
}