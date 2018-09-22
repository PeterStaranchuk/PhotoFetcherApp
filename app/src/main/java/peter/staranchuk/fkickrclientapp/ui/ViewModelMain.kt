package peter.staranchuk.fkickrclientapp.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class ViewModelMain : ViewModel() {
    val isNewImagesLoading = ObservableField<Boolean>(true)
}