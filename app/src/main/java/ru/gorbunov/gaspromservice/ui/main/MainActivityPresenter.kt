package ru.gorbunov.gaspromservice.ui.main

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

class MainActivityPresenter: MainActivityContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainActivityContract.View

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unsubscribe() {
        TODO("Not yet implemented")
    }

    override fun attach(view: MainActivityContract.View) {
        this.view = view
        view.showMainListFragment(Bundle())
    }
}