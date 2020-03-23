package de.andrestefanov.android.nearbuy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import de.andrestefanov.android.nearbuy.*
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

        api = Api()
        hideKeyboardOnTouch()
    }

    /**
     * hides keyboard if no Edittext was touched
     */
    fun hideKeyboardOnTouch() {
        hideKeyboardOnTouch(findViewById(android.R.id.content))
    }

    /**
     * hides keyboard on given view if no edittext was klicked
     * @param view listener on this view
     */
    fun hideKeyboardOnTouch(view: View) {
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

    fun showKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

}
