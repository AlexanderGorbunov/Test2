package ru.gorbunov.gaspromservice.ui.detailedFragment

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.gorbunov.gaspromservice.api.ApiServiceInterface
import ru.gorbunov.gaspromservice.models.MainViewModel
import ru.gorbunov.gaspromservice.ui.main.MainActivity
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListContract
import ru.gorbunov.gaspromservice.utils.Normalization

class DetailedFragmentPresenter: DetailedFragmentContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DetailedFragmentContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailedFragmentContract.View) {
        this.view = view
    }


    override fun getData(vm: ViewModel, type: String?) {
        if (type == null)
            view.showError("Неправильный тип оборудования")
        else
        {
            view.showData(
                Normalization().normalizeDataD(type, (vm as MainViewModel).equipmentList.value)
            )
        }
    }

}