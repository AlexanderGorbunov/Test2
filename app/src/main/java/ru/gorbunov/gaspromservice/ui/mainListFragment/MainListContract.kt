package ru.gorbunov.gaspromservice.ui.mainListFragment

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.models.MainListItem
import ru.gorbunov.gaspromservice.ui.base.BaseContract
import ru.gorbunov.gaspromservice.ui.main.MainActivity

class MainListContract {

    interface View: BaseContract.View {
        fun showData(list: ArrayList<MainListItem>)
        fun saveData(item: EquipmentWrapper)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(activity: FragmentActivity, savedData: EquipmentWrapper?)
    }

}