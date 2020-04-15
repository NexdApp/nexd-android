package app.nexd.android.ui.helper.shoppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.databinding.FragmentShoppingListBinding
import app.nexd.android.ui.helper.shoppingList.ShoppingListFragmentDirections.Companion.toCheckoutFragment
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class ShoppingListFragment : Fragment() {

    private val viewModel: ShoppingListViewModel by viewModels()

    private val adapter = MultiViewAdapter()

    private lateinit var binding: FragmentShoppingListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        shoppingList.layoutManager = LinearLayoutManager(context)
        shoppingList.adapter = adapter

        adapter.registerItemBinders(
            ShoppingListEntryBinder()
        )

        checkout.setOnClickListener {
            findNavController().navigate(toCheckoutFragment())
        }

        viewModel.getShoppingListArticles().observe(viewLifecycleOwner, Observer { shoppingListEntries ->
            val listSection = ListSection<ShoppingListViewModel.ShoppingListEntry>()
            listSection.addAll(shoppingListEntries)
            adapter.addSection(listSection)

            viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
                listSection.setOnSelectionChangedListener { item, isSelected, _ ->
                    if (isSelected)
                        viewModel.checkArticle(shoppingList.id, item.articleId)
                }
            })
        })
    }
}
