package peter.staranchuk.fkickrclientapp

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import peter.staranchuk.fkickrclientapp.di.modules.GlideApp
import peter.staranchuk.fkickrclientapp.model.objects.GlideImageLoader

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.setImageFromUrl(url : String) {
    GlideImageLoader(GlideApp.with(context)).loadImageFromUrl(this, url)
}