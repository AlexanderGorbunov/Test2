package ru.gorbunov.gaspromservice.ui.detailedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.detailed_list_fragment_layout.*
import kotlinx.android.synthetic.main.main_list_fragment_layout.*
import ru.gorbunov.gaspromservice.R
import ru.gorbunov.gaspromservice.models.Consts
import ru.gorbunov.gaspromservice.models.DetailedListItem
import ru.gorbunov.gaspromservice.models.MainViewModel
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListAdapter
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListContract
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListFragment
import ru.gorbunov.gaspromservice.ui.mainListFragment.MainListPresenter

class DetailedFragment: Fragment(), DetailedFragmentContract.View{

    companion object {
        val TAG: String = "DetailedFragment"
    }

    private lateinit var viewModel: MainViewModel

    lateinit var presenter: DetailedFragmentContract.Presenter
    private lateinit var rootView: View

    private var globalType : String? = ""

    fun newInstance(): DetailedFragment {
        return DetailedFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalType = arguments?.getString(Consts.DETAILS_EXTRA, "")
        viewModel = activity?.let {
            ViewModelProviders.of(it)[MainViewModel::class.java]
        } ?: throw Exception("Activity is null")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.detailed_list_fragment_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DetailedFragmentPresenter()
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    private fun initView() {
        presenter.getData(viewModel, globalType)
    }

    override fun showData(list: ArrayList<DetailedListItem>) {
        val adapter = DetailedListAdapter(activity!!, list)
        detailed_list_rv!!.layoutManager = LinearLayoutManager(activity)
        detailed_list_rv!!.adapter = adapter
    }

    override fun showProgress(flag: Boolean) {
        if (flag) {
            detailed_fragmnet_pb.visibility = View.VISIBLE
        } else {
            detailed_fragmnet_pb.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        Toast.makeText(activity, "Ошибка: $error", Toast.LENGTH_LONG).show()
    }
}