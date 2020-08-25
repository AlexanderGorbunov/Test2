package ru.gorbunov.gaspromservice.ui.mainListFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_list_fragment_layout.*
import ru.gorbunov.gaspromservice.R
import ru.gorbunov.gaspromservice.models.Consts
import ru.gorbunov.gaspromservice.models.EquipmentWrapper
import ru.gorbunov.gaspromservice.models.MainListItem
import ru.gorbunov.gaspromservice.models.MainViewModel
import ru.gorbunov.gaspromservice.ui.main.MainActivity

class MainListFragment: Fragment(), MainListContract.View, MainListAdapter.onItemClickListener{

    companion object {
        val TAG: String = "MainListFragment"
    }

    lateinit var presenter: MainListContract.Presenter
    private lateinit var rootView: View

    private lateinit var viewModel: MainViewModel

    fun newInstance(): MainListFragment {
        return MainListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it)[MainViewModel::class.java]
        } ?: throw Exception("Activity is null")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.main_list_fragment_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MainListPresenter()
        presenter.attach(this)
        presenter.subscribe()
        initView()
        //initRV()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun initView() {
        presenter.loadData(activity!!, viewModel.equipmentList.value)
    }

    private fun initRV(list: ArrayList<MainListItem>)
    {
            val adapter = MainListAdapter(activity!!, list, this)
            main_list_rv!!.layoutManager = LinearLayoutManager(activity)
            main_list_rv!!.adapter = adapter
    }

    override fun showProgress(flag: Boolean) {
        if (flag) {
            main_list_progress.visibility = View.VISIBLE
        } else {
            main_list_progress.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        Toast.makeText(activity, "Ошибка: $error", Toast.LENGTH_LONG).show()
    }

    override fun showData(list: ArrayList<MainListItem>) {
        //this.globalList = list
        initRV(list)
    }

    override fun saveData(item: EquipmentWrapper) {
        viewModel.setEquipmentList(item)
    }

    override fun itemDetail(type: String) {
        val extra = Bundle()
        extra.putString(Consts.DETAILS_EXTRA, type)
        (activity as MainActivity).showDetailListFragment(extra)
    }

}