package com.sri.testsen.presenter

import com.sri.testsen.`interface`.MainContract
import com.sri.testsen.`interface`.RetrofitApiService
import com.sri.testsen.model.RealEstateModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RetrofitInteractor(private var mainView: MainContract.MainView) : MainContract.MainPresenter {

    //This method will fetch json data
    override fun getData(compositedisposable: CompositeDisposable) {
        val retrofitService = RetrofitApiService.create()
        compositedisposable.add(retrofitService.loadfeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse))
    }
    private fun handleResponse(response: RealEstateModel) {
        mainView.housingList(response.data.listings)
    }
}