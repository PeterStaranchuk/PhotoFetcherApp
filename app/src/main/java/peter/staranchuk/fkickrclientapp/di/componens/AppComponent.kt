package peter.staranchuk.fkickrclientapp.di.componens

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import peter.staranchuk.fkickrclientapp.PhotoFetcherApp
import peter.staranchuk.fkickrclientapp.di.modules.*
import peter.staranchuk.fkickrclientapp.di.view_model.ViewModelBuilder

@Component(modules = [ViewModelBuilder::class, MainActivityModule::class, FlickrModule::class, AppModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<PhotoFetcherApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: PhotoFetcherApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: DaggerApplication)

}