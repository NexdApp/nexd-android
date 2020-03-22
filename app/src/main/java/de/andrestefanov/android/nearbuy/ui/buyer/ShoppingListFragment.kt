package de.andrestefanov.android.nearbuy.ui.buyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.ShoppingList
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

        viewModel.updateShopppingList()

        val list = ListSection<ShoppingList.Entry>()
        list.addAll(ShoppingList.list)
        adapter.addSection(list)

        checkout.setOnClickListener {
            findNavController().navigate(R.id.checkoutFragment)
        }
    }
}
