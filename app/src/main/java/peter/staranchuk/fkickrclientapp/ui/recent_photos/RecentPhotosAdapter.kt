package peter.staranchuk.fkickrclientapp.ui.recent_photos

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_photo.view.*
import peter.staranchuk.fkickrclientapp.inflate
import peter.staranchuk.fkickrclientapp.model.GeneralPhoto
import peter.staranchuk.fkickrclientapp.setImageFromUrl

class RecentPhotosAdapter(photos : ArrayList<GeneralPhoto>, @LayoutRes val resId : Int, val onClick : (position : Int)-> Unit)
    : RecyclerView.Adapter<RecentPhotosAdapter.ViewHolder>() {

    private val photoList = arrayListOf<GeneralPhoto>()

    init {
        photoList.addAll(photos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(resId))

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.view.setOnClickListener { onClick(position) }
        return viewHolder.setPhoto(photoList[position].getMediumPhotoUrl())
    }

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun setPhoto(photoUrl : String) = view.ivPicture.setImageFromUrl(photoUrl)
    }

    fun addPhotos(newPhotos : List<GeneralPhoto>) {
        photoList.addAll(newPhotos)
        notifyDataSetChanged()
    }
}