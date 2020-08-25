package ru.gorbunov.gaspromservice.ui.base

class BaseContract {
    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun showProgress(flag: Boolean)
        fun showError(error: String)
    }
}