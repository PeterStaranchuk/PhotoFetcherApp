package peter.staranchuk.fkickrclientapp.di.view_model

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

interface ViewModelKeyAnnotation {
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

}