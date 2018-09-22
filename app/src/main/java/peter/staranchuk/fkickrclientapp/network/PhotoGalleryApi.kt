package peter.staranchuk.fkickrclientapp.network

import io.reactivex.Single
import peter.staranchuk.fkickrclientapp.network.responses.FlickrPhotoSizesResponse
import peter.staranchuk.fkickrclientapp.network.responses.RecentPhotosResponse
import peter.staranchuk.fkickrclientapp.network.sources_settings.FlickrSettings
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoGalleryApi {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    fun getRecentPhotos(@Query("per_page") itemsPerPage : Int,
                        @Query("page") numberOfPage : Int,
                        @Query("format") responseFormat : String = "json",
                        @Query("nojsoncallback") noJsonCallback : Int = 1,
                        @Query("api_key") apiKey : String = FlickrSettings().apiKey) : Single<RecentPhotosResponse>

    @GET("/services/rest/?method=flickr.photos.getSizes")
    fun getImageSizes(@Query("photo_id") photoId : String,
                      @Query("format") responseFormat : String = "json",
                      @Query("nojsoncallback") noJsonCallback : Int = 1,
                      @Query("api_key") apiKey : String = FlickrSettings().apiKey) : Single<FlickrPhotoSizesResponse>
}