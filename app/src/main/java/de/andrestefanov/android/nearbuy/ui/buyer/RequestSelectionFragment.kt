package de.andrestefanov.android.nearbuy.ui.buyer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.andrestefanov.android.nearbuy.R

class RequestSelectionFragment : Fragment() {

    companion object {
        fun newInstance() = RequestSelectionFragment()
    }

    private lateinit var viewModel: RequestSelectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buyer_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestSelectionViewModel::class.java)
    }

}
