package peter.staranchuk.fkickrclientapp.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.ui.recent_photos.RecentPhotosFragment
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    lateinit var viewModel : ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ViewModelMain::class.java)

        loadRecentPhotosScreen()
    }

    private fun loadRecentPhotosScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flScreen, RecentPhotosFragment())
                .addToBackStack("")
                .commit()
    }
}
