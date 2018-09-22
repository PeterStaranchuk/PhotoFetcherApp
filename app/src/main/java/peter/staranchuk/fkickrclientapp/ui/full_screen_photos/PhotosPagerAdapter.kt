package peter.staranchuk.fkickrclientapp.ui.full_screen_photos

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import peter.staranchuk.fkickrclientapp.model.GeneralPhoto

class PhotosPagerAdapter(fragmentManager : FragmentManager, val photos : List<GeneralPhoto>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = SingleImageFragment.getInstance(photos[position].getBestPhotoUrl())

    override fun getCount(): Int = photos.size

}