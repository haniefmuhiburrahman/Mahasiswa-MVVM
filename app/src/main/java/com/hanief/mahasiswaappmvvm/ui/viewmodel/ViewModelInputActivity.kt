package com.hanief.mahasiswaappmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanief.mahasiswaappmvvm.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvvm.ui.repo.Repository

class ViewModelInputActivity: ViewModel() {

    val repository = Repository()

    var responseInput = MutableLiveData<ResponseAction>()
    var responseUpdate = MutableLiveData<ResponseAction>()
    var isError = MutableLiveData<Throwable>()


    fun inputData(nama: String, nohp: String, alamat: String) {
        repository.inputData(nama, nohp, alamat, {
            responseInput.value = it
        }, {
            isError.value = it
        }
        )
    }

    fun updateData(id: String, nama: String, noHp: String, alamat: String) {
        repository.updateData(id, nama, noHp, alamat, {
            responseUpdate.value = it
        }, {
            isError.value = it
        }
        )
    }
}