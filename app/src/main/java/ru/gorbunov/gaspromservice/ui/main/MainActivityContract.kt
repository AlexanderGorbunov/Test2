package ru.gorbunov.gaspromservice.ui.main

import android.os.Bundle
import ru.gorbunov.gaspromservice.ui.base.BaseContract

class MainActivityContract {

    interface View: BaseContract.View {
        fun showMainListFragment(extra: Bundle)
        fun showDetailListFragment(extra: Bundle)
    }

    interface Presenter: BaseContract.Presenter<MainActivityContract.View> {

    }

}