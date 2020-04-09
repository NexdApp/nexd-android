package app.nexd.android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.ui.dialog.SelectTextDialog
import io.reactivex.plugins.RxJavaPlugins

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { controller, destination, _ ->
            runOnUiThread {
                // skip authentication if it
                if (destination.id == R.id.authFragment) {
                    Preferences.getToken(this)?.let {
                        controller.navigate(R.id.roleFragment)
                    }
                }
            }
        }

        RxJavaPlugins.setErrorHandler {
            Log.e(MainActivity::class.simpleName, "unhandled error", it)
        }

        api = Api()
        api.setBearerToken(Preferences.getToken(this))
        hideKeyboardOnTouch()
    }

    /**
     * finish activity if start fragment showing
     */
    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).currentDestination?.id == R.id.roleFragment)
            finish()
        else
            super.onBackPressed()
    }

    /**
     * hides keyboard if no editText was touched
     */
    private fun hideKeyboardOnTouch() {
        hideKeyboardOnTouch(findViewById(R.id.nav_host_fragment))
    }

    /**
     * hides keyboard on given view if no editText was clicked
     * @param view listener on this view
     */
    private fun hideKeyboardOnTouch(view: View) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                hideKeyboardOnTouch(innerView)
            }
        }
    }

    /**hides keyboard */
    fun hideKeyboard() {
        if (currentFocus != null) {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus!!.clearFocus()
        }
    }
}
