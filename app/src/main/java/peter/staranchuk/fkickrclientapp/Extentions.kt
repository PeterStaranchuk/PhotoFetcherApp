package peter.staranchuk.fkickrclientapp

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.setImageFromUrl(url : String) {
    Picasso.get().load(url).fit().centerInside().placeholder(R.drawable.image_loading_placeholder).into(this)
}