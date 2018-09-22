package peter.staranchuk.fkickrclientapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import peter.staranchuk.fkickrclientapp.model.repositories.FlickrPhotosRepository
import peter.staranchuk.fkickrclientapp.network.FlickrApi
import peter.staranchuk.fkickrclientapp.network.sources_settings.FlickrSettings
import peter.staranchuk.fkickrclientapp.ui.ViewModelMain

class MainScreenTests {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var flickrApi: FlickrApi

    @Mock
    lateinit var settings : FlickrSettings

    lateinit var viewModelMain : ViewModelMain

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModelMain = Mockito.spy(ViewModelMain(FlickrPhotosRepository(flickrApi, settings)))
    }

    @Test
    fun should_show_progress_bar_when_new_images_loading() {
        viewModelMain.loadNextPage()
        Assert.assertThat(viewModelMain.isNewImagesLoading.get(), CoreMatchers.equalTo(true))
    }


}
