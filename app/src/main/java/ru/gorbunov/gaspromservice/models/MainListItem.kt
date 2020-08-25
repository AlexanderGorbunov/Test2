package ru.gorbunov.gaspromservice.models

import android.graphics.Color

data class MainListItem (
    var name: String = "",
    var workHours: Int = 0,
    var badCondition: Int,
    var goodCondition: Int
)