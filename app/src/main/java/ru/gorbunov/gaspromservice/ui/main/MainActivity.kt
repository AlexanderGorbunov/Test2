package ru.gorbunov.gaspromservice.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.gorbunov.gaspromservice.R
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.ui.detailedFragment.DetailedFragment
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListFragment

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    lateinit var presenter: MainActivityContract.Presenter
    lateinit var loadedData: EquipmentWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter()
        presenter.attach(this)

    }

    override fun showMainListFragment(extra: Bundle) {
        val fragment = MainListFragment().newInstance()
        fragment.arguments = extra
        if (supportFragmentManager.findFragmentByTag(MainListFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.mainFrame, fragment, MainListFragment.TAG)
                .commit()
        }
    }

    override fun showDetailListFragment(extra: Bundle) {
        val fragment = DetailedFragment().newInstance()
        fragment.arguments = extra
        if (supportFragmentManager.findFragmentByTag(DetailedFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainFrame, fragment, DetailedFragment.TAG)
                .commit()
        }
    }

    override fun showProgress(flag: Boolean) {
        if (flag)
            main_activity_pb.visibility = View.VISIBLE
        else
            main_activity_pb.visibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
    }

    fun setMyLoadedData(data: EquipmentWrapper)
    {
        this.loadedData = data
    }

}