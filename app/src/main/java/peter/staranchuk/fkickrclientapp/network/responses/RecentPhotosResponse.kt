package peter.staranchuk.fkickrclientapp.network.responses

import com.google.gson.annotations.SerializedName

data class RecentPhotosResponse(@SerializedName("photos") val photosInfo : PhotosInfo)

data class PhotosInfo(@SerializedName("page") val  currentPageNumber : Int,
                      @SerializedName("pages") val totalNumberOfPages : Int,
                      @SerializedName("perpage") val numberOfImagesPerPage : Int,
                      @SerializedName("photo") val  photos : List<FlickrPhotoInfo>)

data class FlickrPhotoInfo(val id : String)