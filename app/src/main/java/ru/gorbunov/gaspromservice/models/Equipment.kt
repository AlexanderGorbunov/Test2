package ru.gorbunov.gaspromservice.models

import org.simpleframework.xml.*

@Root(name = "Plant", strict=false)
class EquipmentWrapper @JvmOverloads constructor(
    @field:ElementList(inline = true)
    var workshops :List<Workshop> ?= null
)

@Root(name = "Workshop")
class Workshop @JvmOverloads constructor(
    @field:Attribute(name="name") var name :String = "",
    @field:ElementList(inline = true)
    var equipments :List<Equipment> ?= null
)

@Root(name = "Equipment")
class Equipment @JvmOverloads constructor(
    @field:Element(name="id") var id: String = "",
    @field:Element(name="name") var name: String = "",
    @field:Element(name="type") var type: String = "",
    @field:Element(name="good") var state: Boolean = false,
    @field:Element(name="running", required = false) var operating_time: Int = 0
)
