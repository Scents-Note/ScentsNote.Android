package com.afume.afume_android.bindingAdapter

import android.widget.ImageView
import com.afume.afume_android.R
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ImageView, res: Int?) {
        Glide.with(view.context)
            .load(res)
            .into(view)
    }
}