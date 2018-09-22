package peter.staranchuk.fkickrclientapp.di.modules

import dagger.Module
import dagger.Provides
import peter.staranchuk.fkickrclientapp.model.repositories.FlickrPhotosRepository
import peter.staranchuk.fkickrclientapp.network.FlickrApi
import peter.staranchuk.fkickrclientapp.network.sources_settings.FlickrSettings
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class FlickrModule {

    @Provides
    fun provideFlickrPhotosRepository(flickrApi: FlickrApi, settings: FlickrSettings) = FlickrPhotosRepository(flickrApi, settings)

    @Provides
    fun provideSettings() = FlickrSettings()

    @Provides
    fun providesFlickrApi(flickrSettings: FlickrSettings) : FlickrApi {
        return Retrofit.Builder().baseUrl(flickrSettings.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(FlickrApi::class.java)
    }
}