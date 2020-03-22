package de.andrestefanov.android.nearbuy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.network.RestClient
import io.reactivex.plugins.RxJavaPlugins

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            runOnUiThread {
                title = destination.label
            }
        }

        RxJavaPlugins.setErrorHandler {
            Log.e(MainActivity::class.simpleName, "unhandled error", it)
        }

        RestClient.INSTANCE = RestClient(context = this)
    }
}
