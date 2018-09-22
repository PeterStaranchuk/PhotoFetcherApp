package peter.staranchuk.fkickrclientapp.di.modules

import dagger.Module
import dagger.Provides
import peter.staranchuk.fkickrclientapp.PhotoFetcherApp

@Module
class AppModule {

    @Provides
    fun provideContext(application : PhotoFetcherApp) = application.applicationContext
}