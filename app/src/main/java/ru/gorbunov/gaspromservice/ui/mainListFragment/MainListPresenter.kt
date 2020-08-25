package ru.gorbunov.gaspromservice.ui.mainListFragment

import androidx.fragment.app.FragmentActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.gorbunov.gaspromservice.api.ApiServiceInterface
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.models.MainViewModel
import ru.gorbunov.gaspromservice.ui.main.MainActivity
import ru.gorbunov.gaspromservice.utils.Normalization

class MainListPresenter: MainListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: MainListContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainListContract.View) {
        this.view = view
    }


    override fun loadData(activity: FragmentActivity, savedData: EquipmentWrapper?) {
        view.showProgress(true)
        if (savedData == null) {
            val api: ApiServiceInterface = ApiServiceInterface.create()

            var subscribe = api.getEquipments().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showProgress(false)
                    view.saveData(it)
                    view.showData(Normalization().normalizeData(it))
                    (activity as MainActivity).setMyLoadedData(it)
                }, {
                    view.showProgress(false)
                    view.showError(it.localizedMessage)
                }
                )
            subscriptions.add(subscribe)
        }
        else
        {
            view.showProgress(false)
            view.showData(Normalization().normalizeData(savedData))
        }
    }
}