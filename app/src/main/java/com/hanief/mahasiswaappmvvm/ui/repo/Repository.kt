package com.hanief.mahasiswaappmvvm.ui.repo

import com.hanief.mahasiswaappmvvm.network.ConfigNetwork
import com.hanief.mahasiswaappmvvm.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvvm.ui.model.getdata.ResponseGetData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository {

    fun getData(responHandler: (ResponseGetData) -> Unit, errorHandler: (Throwable) -> Unit) {

        ConfigNetwork.getRetrofit().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun hapusData(id: String?, respondHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit ) {

        ConfigNetwork.getRetrofit().deleteData(id ?: "").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                respondHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun inputData(nama: String, nohp: String, alamat: String, respondHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit) {

        if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {
            ConfigNetwork.getRetrofit().insertData(nama, nohp, alamat).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        respondHandler(it)
                    }, {
                        errorHandler(it)
                    })
        } else {
            errorHandler
        }
    }

    fun updateData(id: String, nama: String, noHp: String, alamat: String, respondHandler: (ResponseAction) -> Unit, errorHandler: (Throwable) -> Unit) {

        if (id.isNotEmpty() && nama.isNotEmpty() && noHp.isNotEmpty() && alamat.isNotEmpty()) {
            ConfigNetwork.getRetrofit().updateData(id, nama, noHp, alamat).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        respondHandler(it)
                    }, {
                        errorHandler(it)
                    })
        } else {
            errorHandler
        }

    }


}