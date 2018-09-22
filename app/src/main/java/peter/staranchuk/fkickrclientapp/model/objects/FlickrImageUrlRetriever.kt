package peter.staranchuk.fkickrclientapp.model.objects

import peter.staranchuk.fkickrclientapp.network.responses.FlickrImageSize
import peter.staranchuk.fkickrclientapp.network.sources_settings.FlickrSettings

class FlickrImageUrlRetriever(private val imageSizes : List<FlickrImageSize>, private val settings : FlickrSettings) {

    fun getBestQualityImageUrl() : String = imageSizes.last().imageUrl

    fun getMediumQualityImageUrl() : String {
        val mediumQuality = imageSizes.filter { it.label == settings.photoSizeMedium }
        if(mediumQuality.isNotEmpty()) {
            return mediumQuality.first().imageUrl
        }

        return imageSizes.last().imageUrl
    }
}