package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

class ShoppingListViewModel : ViewModel() {

    class ShoppingListEntry(var name: String, var amount: Int) {
        var collected = false
    }

    fun getItems(): LiveData<List<ShoppingListEntry>> {

        val observable = api.articlesControllerFindAll()
            .flatMap { articles ->
                api.requestControllerGetAll(null, null)
                    .map { requests ->
                        requests
                            .flatMap { it.articles.filter { article -> article.articleCount > BigDecimal.ZERO } }
                            .map { article ->
                                ShoppingListEntry(
                                    articles.first { article.articleId == it.id.toBigDecimal() }.name,
                                    article.articleCount.toInt()
                                )
                            }
                    }
            }
            .subscribeOn(Schedulers.io())

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }


}