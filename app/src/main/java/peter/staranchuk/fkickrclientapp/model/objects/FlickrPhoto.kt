package peter.staranchuk.fkickrclientapp.model.objects

import peter.staranchuk.fkickrclientapp.model.GeneralPhoto

class FlickrPhoto(private val urlRetriever : FlickrImageUrlRetriever) : GeneralPhoto {

    override fun getBestPhotoUrl(): String = urlRetriever.getBestQualityImageUrl()

    override fun getMediumPhotoUrl(): String = urlRetriever.getMediumQualityImageUrl()

}