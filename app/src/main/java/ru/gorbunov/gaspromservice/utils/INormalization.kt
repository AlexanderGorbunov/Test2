package ru.gorbunov.gaspromservice.utils

import ru.gorbunov.gaspromservice.models.DetailedListItem
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.models.MainListItem

interface INormalization {

    fun normalizeData(list: EquipmentWrapper): ArrayList<MainListItem>
    fun normalizeDataD(type : String, list: EquipmentWrapper?): ArrayList<DetailedListItem>

}