package ru.samitin.weather.utils.network

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest


fun ImageView.loadUrl(url: String) {


    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry {
            add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.error)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}