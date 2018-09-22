package peter.staranchuk.fkickrclientapp.ui.full_screen_photos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_full_screen_photos.*
import peter.staranchuk.fkickrclientapp.Extras
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.inflate
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain

class FullScreenPhotosFragment : Fragment() {

    lateinit var viewModel: ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ViewModelMain::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = container?.inflate(R.layout.fragment_full_screen_photos)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photos.observe(this, Observer { newPhotos ->
            newPhotos?.let {
                vpPhotos.adapter = PhotosPagerAdapter(childFragmentManager, newPhotos)
                vpPhotos.currentItem = arguments?.getInt(Extras().currentPhotoPosition) ?: 0
            }
        })
    }

    companion object {
        fun getInstance(currentPhotoPosition: Int?): FullScreenPhotosFragment {
            val fullScreenPhotosFragment = FullScreenPhotosFragment()
            val bundle = Bundle()
            bundle.putInt(Extras().currentPhotoPosition, currentPhotoPosition ?: 0)
            fullScreenPhotosFragment.arguments = bundle
            return fullScreenPhotosFragment
        }
    }
}