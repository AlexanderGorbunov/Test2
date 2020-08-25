package ru.gorbunov.gaspromservice.utils

import android.graphics.Color
import ru.gorbunov.gaspromservice.models.DetailedListItem
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.models.MainListItem

class Normalization : INormalization{
    override fun normalizeData(list: EquipmentWrapper): ArrayList<MainListItem> {
        val normalizedData = arrayListOf<MainListItem>()
        val items = HashMap<String, MainListItem>()
        list.workshops?.forEach { workshop ->
            workshop.equipments?.forEach {
                if (items.containsKey(it.type))
                {
                    if (items[it.type]?.workHours!! < it.operating_time)
                        items[it.type]?.workHours = it.operating_time

                    if (it.state)
                        items[it.type]!!.goodCondition += 1
                    else
                        items[it.type]!!.badCondition += 1
                }
                else
                {
                    val equipmentItem = MainListItem(
                        it.type,
                        it.operating_time,
                        if (it.state) 0 else 1,
                        if (it.state) 1 else 0)
                    items.put(it.type, equipmentItem)
                }
            }
        }

        return ArrayList(items.values)
    }

    override fun normalizeDataD(type: String, list: EquipmentWrapper?): ArrayList<DetailedListItem> {
        val items = arrayListOf<DetailedListItem>()

        list?.workshops?.forEach { workshop ->
            workshop.equipments?.forEach {
                if (it.type == type)
                    items.add(DetailedListItem(it.name, workshop.name, it.operating_time, it.state))
            }
        }

        return items
    }

}