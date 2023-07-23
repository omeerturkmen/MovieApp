package com.example.myapplication2.core.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setPhoto")
fun setPhoto(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrEmpty().not()){
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w500$imageUrl").fitCenter().into(view)
    }
}