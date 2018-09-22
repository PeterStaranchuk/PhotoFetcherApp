package peter.staranchuk.fkickrclientapp.ui.full_screen_photos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.inflate

class PhotosLoadingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = container?.inflate(R.layout.fragment_layout_loading)

}