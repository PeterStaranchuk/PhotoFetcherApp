package peter.staranchuk.fkickrclientapp.model.objects

import android.widget.ImageView
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.di.modules.GlideRequests

class GlideImageLoader(val glide: GlideRequests) {

    fun loadImageFromUrl(image: ImageView, url: String) {
       glide.load(url)
               .centerInside()
               .thumbnail(0.1f)//load smaller version of image (10% of original size/quality)
               .disallowHardwareConfig() //increase speed on models from Android O (use hardware bitmaps) more: http://bumptech.github.io/glide/doc/hardwarebitmaps.html
               .placeholder(R.drawable.image_loading_placeholder)
               .into(image)
    }
}