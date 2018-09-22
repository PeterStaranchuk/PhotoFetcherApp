package peter.staranchuk.fkickrclientapp.ui.full_screen_photos

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import peter.staranchuk.fkickrclientapp.model.GeneralPhoto

class PhotosPagerAdapter(fragmentManager: FragmentManager, photos: List<GeneralPhoto>, val onEndReachedAt: (position: Int) -> Unit) : FragmentPagerAdapter(fragmentManager) {

    private val photoList = arrayListOf<GeneralPhoto>()

    init {
        photoList.addAll(photos)
    }

    override fun getItem(position: Int): Fragment {
        if (position == photoList.size - 1) {
            onEndReachedAt(position - 1)
        }
        return SingleImageFragment.getInstance(photoList[position].getBestPhotoUrl())
    }

    override fun getCount(): Int = Int.MAX_VALUE

    fun addPhotos(newPhotos : List<GeneralPhoto>) {
        photoList.addAll(newPhotos)
        notifyDataSetChanged()
    }
}