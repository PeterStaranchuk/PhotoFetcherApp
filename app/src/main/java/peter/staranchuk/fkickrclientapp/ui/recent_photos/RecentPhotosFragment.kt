package peter.staranchuk.fkickrclientapp.ui.recent_photos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_layout_recent_photos.*
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.databinding.FragmentLayoutRecentPhotosBinding
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain

class RecentPhotosFragment : Fragment() {

    private lateinit var viewModel : ViewModelMain
    private lateinit var recentPhotosAdapter : RecentPhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ViewModelMain::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLayoutRecentPhotosBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNextPage()

        recentPhotosAdapter = RecentPhotosAdapter(arrayListOf(), R.layout.item_photo) { numberOfLickedPhoto->
            viewModel.onOpenFullScreenPhoto(numberOfLickedPhoto)
        }

        val gridLayoutManager = GridLayoutManager(context, resources.getInteger(R.integer.spanCount))

        rvPhotos.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = recentPhotosAdapter
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    viewModel.loadNextPage()
                }
            })
        }

        viewModel.photos.observe(this, Observer {newPhotos->
            newPhotos?.let { recentPhotosAdapter.addPhotos(newPhotos) }
        })

        viewModel.onSetTitle(R.string.titleRecentPhotos)
    }

}