package peter.staranchuk.fkickrclientapp.ui.recent_photos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import peter.staranchuk.fkickrclientapp.databinding.FragmentLayoutRecentPhotosBinding
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain

class RecentPhotosFragment : Fragment() {

    private lateinit var viewModel : ViewModelMain

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

        viewModel.photos.observe(this, Observer {
            //TODO add list loading and update
        })
    }
}