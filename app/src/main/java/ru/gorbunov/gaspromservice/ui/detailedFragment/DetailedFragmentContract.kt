package ru.gorbunov.gaspromservice.ui.detailedFragment

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import ru.gorbunov.gaspromservice.models.DetailedListItem
import ru.gorbunov.gaspromservice.models.MainListItem
import ru.gorbunov.gaspromservice.ui.base.BaseContract

class DetailedFragmentContract {

    interface View: BaseContract.View {
        fun showData(list: ArrayList<DetailedListItem>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getData(vm: ViewModel, type: String?)
    }

}