package peter.staranchuk.fkickrclientapp.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import peter.staranchuk.fkickrclientapp.di.view_model.ViewModelKeyAnnotation
import peter.staranchuk.fkickrclientapp.MainActivity
import peter.staranchuk.fkickrclientapp.ViewModelMain

@Module
internal abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKeyAnnotation.ViewModelKey(ViewModelMain::class)
    abstract fun bindMainViewModel(viewModel: ViewModelMain): ViewModel

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}