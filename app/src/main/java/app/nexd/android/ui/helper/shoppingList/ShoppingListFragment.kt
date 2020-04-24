package app.nexd.android.ui.helper.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {

    private val viewModel: ShoppingListViewModel by viewModel()
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

        viewModel.getShoppingListArticles().observe(viewLifecycleOwner, Observer { shoppingListEntries ->
            val listSection = ListSection<ShoppingListViewModel.ShoppingListEntry>()
            listSection.addAll(shoppingListEntries)
            adapter.addSection(listSection)

            checkout.setOnClickListener {
                findNavController().navigate(ShoppingListFragmentDirections.toCheckoutFragment())
            }

            viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
                listSection.setOnSelectionChangedListener { item, isSelected, _ ->
                    if (isSelected)
                        viewModel.checkArticle(shoppingList.id, item.articleId)
                }
            })
        })
    }
}
