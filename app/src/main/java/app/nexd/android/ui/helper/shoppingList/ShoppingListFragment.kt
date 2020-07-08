package app.nexd.android.ui.helper.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.databinding.FragmentShoppingListBinding
import app.nexd.android.ui.helper.shoppingList.ShoppingListFragmentDirections.Companion.toCheckoutFragment
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.extension.decorator.DividerDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListFragment : Fragment() {

    private val viewModel: ShoppingListViewModel by viewModel()

    private lateinit var binding: FragmentShoppingListBinding

    private lateinit var adapter: MultiViewAdapter

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

        adapter = MultiViewAdapter()
        recyclerView_shoppingList.addItemDecoration(adapter.itemDecoration)
        recyclerView_shoppingList.layoutManager = LinearLayoutManager(context)
        recyclerView_shoppingList.adapter = adapter

        val binder = ShoppingListEntryBinder()
        binder.addDecorator(DividerDecoration(adapter, context, DividerDecoration.VERTICAL))
        adapter.registerItemBinders(
            binder
        )

        viewModel.getShoppingListArticles().observe(viewLifecycleOwner, Observer { shoppingListEntries ->
            adapter.removeAllSections()
            val listSection = ListSection<ShoppingListViewModel.ShoppingListEntry>()
            listSection.addAll(shoppingListEntries)
            adapter.addSection(listSection)

            button_checkout.setOnClickListener {
                findNavController().navigate(toCheckoutFragment())
            }

            viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
                listSection.setOnSelectionChangedListener { item, isSelected, _ ->
                    if (isSelected)
                        viewModel.checkArticle(shoppingList.id, item.articleId)
                }
            })
        })

        toolbar_back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
