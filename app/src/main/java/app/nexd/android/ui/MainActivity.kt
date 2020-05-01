package app.nexd.android.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import app.nexd.android.R
import app.nexd.android.ui.auth.AuthFragmentDirections
import io.reactivex.plugins.RxJavaPlugins
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { controller, destination, _ ->
            Log.v("Navigation", destination.toString())
            runOnUiThread {
                // skip authentication if it
                if (destination.id == R.id.authFragment) {
                    if (mainViewModel.isAuthenticated()) {
                        Log.v("Navigation", "redirect to roleFragment")
                        controller.navigate(AuthFragmentDirections.actionAuthFragmentToRoleFragmentOnAuthValid())
                    }
                }
            }
        }

        RxJavaPlugins.setErrorHandler {
            Log.e(MainActivity::class.simpleName, "unhandled error", it)
        }

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
