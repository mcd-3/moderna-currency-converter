package com.matthew.carvalhodagenais.modernaconverter.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.matthew.carvalhodagenais.modernaconverter.R

/**
 * Helper class to handle swapping images
 *
 * @param context Context
 */
class ImageViewSwapperUtil(private val context: Context) {

    private val gifSrc: String = "file:///android_asset/loading.gif"

    /**
     * Swaps the src of an ImageView to a loading gif
     *
     * @param imageView ImageView
     */
    fun swapToLoading(imageView: ImageView) {
        Glide.with(context)
            .load(gifSrc)
            .into(imageView)
    }

    /**
     * Swaps the src of an ImageView to a static "swap" drawable
     *
     * @param imageView ImageView
     */
    fun swapToStatic(imageView: ImageView) {
        Glide.with(context)
            .load(context.getDrawable(R.drawable.ic_baseline_swap_48))
            .into(imageView)
    }

}