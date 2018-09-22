package peter.staranchuk.fkickrclientapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import peter.staranchuk.fkickrclientapp.di.componens.DaggerAppComponent

class PhotoFetcherApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().context(this).build()

}