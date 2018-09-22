package peter.staranchuk.fkickrclientapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import peter.staranchuk.fkickrclientapp.model.objects.FlickrImageUrlRetriever
import peter.staranchuk.fkickrclientapp.model.objects.FlickrPhoto
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

    @Mock
    lateinit var flickrRepository : FlickrPhotosRepository

    lateinit var viewModelMain : ViewModelMain

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModelMain = Mockito.spy(ViewModelMain(flickrRepository))
    }

    @Test
    fun should_show_progress_bar_when_new_images_loading() {
        `when`(flickrRepository.getPhotos(1)).thenReturn(Single.create {emitter ->
            val photo = FlickrPhoto(FlickrImageUrlRetriever(arrayListOf(), settings))
            emitter.onSuccess(arrayListOf(photo))
        })

        viewModelMain.loadNextPage()

        Assert.assertThat(viewModelMain.isNewImagesLoading.get(), CoreMatchers.equalTo(false))
    }

    @Test
    fun should_load_one_element_from_repository() {
        `when`(flickrRepository.getPhotos(1)).thenReturn(Single.create {emitter ->
            val photo = FlickrPhoto(FlickrImageUrlRetriever(arrayListOf(), settings))
            emitter.onSuccess(arrayListOf(photo))
        })

        viewModelMain.loadNextPage()

        Assert.assertThat(viewModelMain.photos.value!!.size, CoreMatchers.equalTo(1))
    }
}
