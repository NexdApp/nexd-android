package app.nexd.android.ui.seeker.create.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nexd.android.api.model.Unit
import app.nexd.android.databinding.ViewArticleUnitBinding
import java.util.*


class ArticleUnitsAdapter : RecyclerView.Adapter<ArticleUnitViewHolder>() {

    private var data = emptyList<Unit>()

    private var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleUnitViewHolder {
        val binding =
            ViewArticleUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleUnitViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ArticleUnitViewHolder, position: Int) {
        holder.binding.apply {
            unit = data[position]
            root.setOnClickListener {
                val pos = holder.adapterPosition
                Collections.swap(data, pos, 0)
                notifyItemMoved(pos, 0)
            }
        }
    }

    fun setData(units: List<Unit>) {
        data = units
        notifyDataSetChanged()
    }

    fun getCheckedUnit() = data[checkedPosition]

}

class ArticleUnitViewHolder(val binding: ViewArticleUnitBinding) :
    RecyclerView.ViewHolder(binding.root)