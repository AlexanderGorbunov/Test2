package ru.gorbunov.gaspromservice.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    var equipmentList = MutableLiveData<EquipmentWrapper>()

    fun setEquipmentList(item: EquipmentWrapper)
    {
        this.equipmentList.value = item
    }

}