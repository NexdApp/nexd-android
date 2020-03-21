package de.andrestefanov.android.nearbuy.ui.seeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.andrestefanov.android.nearbuy.R
import kotlinx.android.synthetic.main.seeker_overview_fragment.*

class CreatedRequestsFragment : Fragment() {

    companion object {
        fun newInstance() = CreatedRequestsFragment()
    }

    private lateinit var viewModel: CreatedRequestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.seeker_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreatedRequestsViewModel::class.java)

        requester_list.adapter
    }

}
