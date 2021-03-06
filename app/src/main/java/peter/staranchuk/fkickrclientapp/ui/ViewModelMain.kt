package peter.staranchuk.fkickrclientapp.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import peter.staranchuk.fkickrclientapp.model.GeneralPhoto
import peter.staranchuk.fkickrclientapp.model.repositories.FlickrPhotosRepository
import javax.inject.Inject

class ViewModelMain @Inject constructor(private val flickrPhotoRepository: FlickrPhotosRepository) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val photos = MutableLiveData<List<GeneralPhoto>>()
    var flickrPageNumber = 1 //page numbers in Flickr start from 0 but pages with numbers 0 and 1 are the same

    val isGeneralErrorVisible = ObservableField<Boolean>(false)
    val isNewImagesLoading = ObservableField<Boolean>(true)
    val isPhotosListVisible = ObservableField<Boolean>(false)

    var onOpenFullScreenPhoto : (currentPhotoPosition : Int) -> Unit = {position : Int -> /*nothing to do*/}
    var onSetTitle : (titleId : Int) -> Unit = { titleId -> /*nothing to do*/ }
    var onBackButtonPressed : () -> Unit = {}

    init {
        photos.value = arrayListOf()
    }

    fun loadNextPage() {
        loadPage(flickrPageNumber++)
    }

    fun reloadPage() {
        loadPage(flickrPageNumber)
    }

    private fun loadPage(numberOfPage : Int) {
        isGeneralErrorVisible.set(false)
        isNewImagesLoading.set(true)
        compositeDisposable.add(flickrPhotoRepository.getPhotos(numberOfPage)
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ newPhotos ->
                    val photoList = arrayListOf<GeneralPhoto>()
                    photoList.addAll(photos.value!!)
                    photoList.addAll(newPhotos)
                    photos.value = photoList
                    isPhotosListVisible.set(true)
                    isNewImagesLoading.set(false)
                }, {
                    isPhotosListVisible.set(false)
                    isGeneralErrorVisible.set(true)
                    isNewImagesLoading.set(false)
                }))
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}