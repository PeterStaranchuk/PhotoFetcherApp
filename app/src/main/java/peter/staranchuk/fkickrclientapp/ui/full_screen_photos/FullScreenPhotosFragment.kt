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
import peter.staranchuk.fkickrclientapp.databinding.FragmentFullScreenPhotosBinding
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain

class FullScreenPhotosFragment : Fragment() {

    lateinit var viewModel: ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ViewModelMain::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFullScreenPhotosBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoAdapter = PhotosPagerAdapter(childFragmentManager, arrayListOf()) { endPosition->
            viewModel.loadNextPage()
            arguments?.putInt(Extras().currentPhotoPosition, endPosition)
        }

        observeNewPhotosLoading(photoAdapter)
        setTitle()

        btnBack.setOnClickListener { viewModel.onBackButtonPressed() }
    }

    private fun setTitle() {
        viewModel.onSetTitle(R.string.titlePhotos)
    }

    private fun observeNewPhotosLoading(photoAdapter: PhotosPagerAdapter) {
        viewModel.photos.observe(this, Observer { newPhotos ->
            newPhotos?.let {
                photoAdapter.addPhotos(newPhotos)
                vpPhotos.adapter = photoAdapter
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