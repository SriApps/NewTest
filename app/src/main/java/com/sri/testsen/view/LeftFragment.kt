package com.sri.testsen.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.sri.testsen.*
import com.sri.testsen.adapter.LeftFragmentAdapter
import com.sri.testsen.`interface`.MainContract
import com.sri.testsen.model.Listings
import com.sri.testsen.presenter.RetrofitInteractor
import io.reactivex.disposables.CompositeDisposable

class LeftFragment : Fragment(), MainContract.MainView {

    private var compositedisposable: CompositeDisposable? = CompositeDisposable()
    private var dataList: List<Listings>? = null
    internal var view: View? = null
    private lateinit var recyclerView: RecyclerView
    private var rvAdapter: RecyclerView.Adapter<*>? = null
    private lateinit var mainPresenter: MainContract.MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragment is not destroyed if retainInstance is true on orientation change
        retainInstance = true
        //this condition will check if we have json data if yes data will not be re downloaded.
        view = inflater.inflate(R.layout.left_fragment, container, false)
        recyclerView = view!!.findViewById(R.id.left_fragment_RV)
        mainPresenter = RetrofitInteractor(this)

        // show it
        if (dataList == null) {
            mainPresenter.getData(this.compositedisposable!!)
        } else {
            initRecyclerView()
        }
        return view
    }

    //Initiallizing recycler view
    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        rvAdapter = LeftFragmentAdapter(dataList!!, this.activity!!)
        recyclerView.adapter = rvAdapter
    }

    override fun housingList(dataList: List<Listings>) {
        this.dataList = dataList
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Compositedisposable is cleared once the fragment is destroyed
        compositedisposable!!.clear()
    }
}