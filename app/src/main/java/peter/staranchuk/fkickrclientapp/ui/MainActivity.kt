package peter.staranchuk.fkickrclientapp.ui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import peter.staranchuk.fkickrclientapp.R
import peter.staranchuk.fkickrclientapp.ui.full_screen_photos.FullScreenPhotosFragment
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

        viewModel.onOpenFullScreenPhoto = { currentPhotoPosition: Int ->
            loadFullPhotosScreen(currentPhotoPosition)
        }
    }

    private fun loadFullPhotosScreen(currentPhotoPosition: Int?) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flScreen, FullScreenPhotosFragment.getInstance(currentPhotoPosition))
                .addToBackStack("")
                .commit()
    }

    private fun loadRecentPhotosScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flScreen, RecentPhotosFragment())
                .addToBackStack("")
                .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
