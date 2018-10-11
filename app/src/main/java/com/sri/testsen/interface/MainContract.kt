package com.sri.testsen.`interface`

import com.sri.testsen.model.Listings
import io.reactivex.disposables.CompositeDisposable

interface MainContract {

    // Interface for the presenter
    interface MainPresenter {
        fun getData(compositedisposable:CompositeDisposable)
    }

    //Interface for the View
    interface MainView {
        fun housingList(dataList: List<Listings>)
    }
}
