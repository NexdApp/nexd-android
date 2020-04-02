package app.nexd.android.ui.helper.shoppinglist

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.api.model.ShoppingList
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal

class ShoppingListViewModel : ViewModel() {

    class ShoppingListEntry(var name: String, var amount: Int) : Parcelable {
        var collected = false

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(amount)
            parcel.writeBoolean(collected)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ShoppingListEntry> {
            override fun createFromParcel(parcel: Parcel): ShoppingListEntry {
                val shoppingList = ShoppingListEntry(
                    parcel.readString()!!,
                    parcel.readInt()
                )
                shoppingList.collected = parcel.readBoolean()
                return shoppingList
            }

            override fun newArray(size: Int): Array<ShoppingListEntry?> {
                return arrayOfNulls(size)
            }
        }
    }

    fun getShoppingList(): LiveData<List<ShoppingList>> {
        return LiveDataReactiveStreams.fromPublisher(api.shoppingListControllerGetUserLists()
            .toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getItems(): LiveData<List<ShoppingListEntry>> {

        val observable = api.articlesControllerFindAll()
            .flatMap { articles ->
                api.requestControllerGetAll(null, null)
                    .map { requests -> requests.filter { it.status == RequestEntity.StatusEnum.ONGOING } }

                    .flatMapIterable { requests ->
                        val shoppingList = mutableMapOf<BigDecimal, ShoppingListEntry>()
                        requests.forEach { request ->
                            request.articles.filter { it.articleCount > BigDecimal.ZERO }.forEach { article ->
                                if (shoppingList.containsKey(article.articleId)) {
                                    shoppingList[article.articleId]!!.amount += article.articleCount.toInt()
                                } else {
                                    shoppingList[article.articleId] = ShoppingListEntry(
                                        articles.first { article.articleId == it.id.toBigDecimal() }.name,
                                        article.articleCount.toInt()
                                    )
                                }
                            }
                        }
                        shoppingList.values
                    }.toList().toObservable()
            }
            .subscribeOn(Schedulers.io())

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }


}