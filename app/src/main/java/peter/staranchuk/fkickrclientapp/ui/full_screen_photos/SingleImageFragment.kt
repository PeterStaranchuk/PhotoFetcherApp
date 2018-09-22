package peter.staranchuk.fkickrclientapp.ui.full_screen_photos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_full_screen_photo.*
import peter.staranchuk.fkickrclientapp.Extras
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.inflate
import peter.staranchuk.fkickrclientapp.setImageFromUrl

class SingleImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.item_full_screen_photo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivPicture.setImageFromUrl(arguments?.getString(Extras().photoUrl)?:"")
    }

    companion object {
        fun getInstance(photoUrl : String) : SingleImageFragment {
            val singleImageFragment = SingleImageFragment()
            val bundle = Bundle()
            bundle.putString(Extras().photoUrl, photoUrl)
            singleImageFragment.arguments = bundle
            return singleImageFragment
        }
    }

}