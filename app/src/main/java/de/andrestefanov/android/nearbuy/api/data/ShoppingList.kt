package de.andrestefanov.android.nearbuy.api.data

abstract class ShoppingList {

    class Entry(var name: String, var amount: Int, var requestItems: MutableList<HelpRequestItem>) {

        fun setCollected(collected: Boolean) {
            for (requestItem in requestItems) {
                requestItem.collected = collected
            }
        }
    }

    companion object {
        var list: MutableList<Entry> = mutableListOf()
            private set

        fun getEntry(name: String): Entry? {
            for (entry in list) {
                if (entry.name == name) {
                    return entry
                }
            }
            return null
        }

        fun addEntry(name: String, amount: Int, requestItem: HelpRequestItem) {
            val entry = getEntry(name)
            if (entry != null) {
                entry.amount += amount
                entry.requestItems.add(requestItem)
            } else {
                list.add(Entry(name, amount, mutableListOf(requestItem)))
            }
        }

        fun getEntriesForRequest(requestId: String): List<Entry> {
            val collectedItems = mutableListOf<Entry>()
            for (entry in list) {
                /*if (entry.requestItems.contains(requestId)) {
                    collectedItems.add(entry)
                }*/
            }
            return collectedItems
        }

        fun fromRequests(requests: List<HelpRequest>) {
            list.clear()

            for (request in requests) {
                loop@ for (item in request.items) {
                    addEntry(item.name, item.amount, item)
                }
            }
        }
    }

}