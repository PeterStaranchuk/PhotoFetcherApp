package peter.staranchuk.fkickrclientapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dagger.android.DaggerActivity
import peter.staranchuk.fkickrclientapp.R

class MainActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
