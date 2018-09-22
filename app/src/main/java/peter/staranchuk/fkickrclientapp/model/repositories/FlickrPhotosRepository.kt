package peter.staranchuk.fkickrclientapp.model.repositories

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import peter.staranchuk.fkickrclientapp.model.objects.FlickrImageUrlRetriever
import peter.staranchuk.fkickrclientapp.model.objects.FlickrPhoto
import peter.staranchuk.fkickrclientapp.network.FlickrApi
import peter.staranchuk.fkickrclientapp.network.sources_settings.FlickrSettings
import javax.inject.Inject

class FlickrPhotosRepository @Inject constructor(private val flickrApi: FlickrApi, private val settings: FlickrSettings) {

    fun getPhotos(pageNumber: Int): Single<List<FlickrPhoto>> {
        return flickrApi.getRecentPhotos(
                itemsPerPage = settings.numberOfPhotoPerPage,
                numberOfPage = pageNumber).toFlowable()
                .map { response -> response.photosInfo }
                .flatMapIterable { photosInfo -> photosInfo.photos }
                .map { photo -> photo.id }
                .flatMap { photoId ->
                    flickrApi.getImageSizes(photoId).toFlowable()
                            .map { response -> response.sizeInfo }
                            .map { sizeInfo -> sizeInfo.photoSizes }
                            .map { sizes -> FlickrImageUrlRetriever(sizes, settings) }
                            .map { urlRetriever -> FlickrPhoto(urlRetriever) }.subscribeOn(Schedulers.newThread())
                }.toList().subscribeOn(Schedulers.newThread())
    }
}