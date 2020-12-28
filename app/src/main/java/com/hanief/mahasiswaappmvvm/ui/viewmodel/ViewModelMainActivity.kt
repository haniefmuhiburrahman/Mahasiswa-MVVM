package com.hanief.mahasiswaappmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanief.mahasiswaappmvvm.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvvm.ui.model.getdata.ResponseGetData
import com.hanief.mahasiswaappmvvm.ui.repo.Repository

class ViewModelMainActivity: ViewModel() {

    val repository = Repository()

    var responseData = MutableLiveData<ResponseGetData>()
    var responseAction = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()

    fun getListData() {
        repository.getData({
            isLoading.value = false
            responseData.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }

    fun deleteData(id: String) {
        repository.hapusData(id, {
            responseAction.value = it
            isLoading.value = false
            getListData()
        }, {
            isError.value = it
            isLoading.value = false
        })
    }

}