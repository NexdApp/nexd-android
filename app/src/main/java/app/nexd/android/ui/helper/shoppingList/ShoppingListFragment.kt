package app.nexd.android.ui.helper.shoppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class ShoppingListFragment : Fragment() {

    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MultiViewAdapter()
        shoppingList.layoutManager = LinearLayoutManager(context)
        shoppingList.adapter = adapter

        adapter.registerItemBinders(
            ShoppingListEntryBinder()
        )

        viewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)

        viewModel.getItems().observe(viewLifecycleOwner, Observer { shoppingList ->
            val listSection = ListSection<ShoppingListViewModel.ShoppingListEntry>()
            listSection.addAll(shoppingList)
            adapter.addSection(listSection)

            checkout.setOnClickListener {
                findNavController().navigate(ShoppingListFragmentDirections.actionShoppingListFragmentToCheckoutFragment(shoppingList.toTypedArray()))
            }
        })
    }
}
