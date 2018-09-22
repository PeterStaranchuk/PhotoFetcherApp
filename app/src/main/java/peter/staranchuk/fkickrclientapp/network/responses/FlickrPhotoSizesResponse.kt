package peter.staranchuk.fkickrclientapp.network.responses

import com.google.gson.annotations.SerializedName

data class FlickrPhotoSizesResponse(@SerializedName("sizes") val sizeInfo : FlickrSizesInfo)

data class FlickrSizesInfo(@SerializedName("size") val photoSizes : List<FlickrImageSize>)

data class FlickrImageSize(val label : String, val width : String, val height : String, @SerializedName("source") val imageUrl : String)