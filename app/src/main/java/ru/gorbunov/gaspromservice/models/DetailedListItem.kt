package ru.gorbunov.gaspromservice.models

data class DetailedListItem(
    var name: String ="",
    var workshop: String = "",
    var work_hours: Int = 0,
    var condition: Boolean = false
)