package peter.staranchuk.fkickrclientapp.ui.recent_photos

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import peter.staranchuk.fkickrclientapp.databinding.FragmentLayoutRecentPhotosBinding
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain
import javax.inject.Inject

class RecentPhotosFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : ViewModelMain

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLayoutRecentPhotosBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ViewModelMain::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }


}