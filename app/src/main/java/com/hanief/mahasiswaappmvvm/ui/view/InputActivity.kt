package com.hanief.mahasiswaappmvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hanief.mahasiswaappmvvm.R
import com.hanief.mahasiswaappmvvm.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvvm.ui.model.getdata.DataItem
import com.hanief.mahasiswaappmvvm.ui.viewmodel.ViewModelInputActivity
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelInputActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        viewModel = ViewModelProviders.of(this).get(ViewModelInputActivity::class.java)

        val getData = intent.getParcelableExtra<DataItem>("data")
        if (getData != null) {
            et_nama.setText(getData.mahasiswaNama)
            et_nohp.setText(getData.mahasiswaNohp)
            et_alamat.setText(getData.mahasiswaAlamat)
            bt_simpan.text = "update"
        }

        when (bt_simpan.text) {
            "Update" -> {
                bt_simpan.setOnClickListener {
                    getData?.idMahasiswa?.let { it1 ->
                        viewModel.updateData(
                                it1,
                                et_nama.text.toString(),
                                et_nohp.text.toString(),
                                et_alamat.text.toString()
                        )
                    }
                }
            }
            else -> {
                bt_simpan.setOnClickListener {
                    viewModel.inputData(
                            et_nama.text.toString(),
                            et_nohp.text.toString(),
                            et_alamat.text.toString()
                    )
                }
            }
        }

        bt_batal.setOnClickListener {
            finish()
        }

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseInput.observe(this, Observer { inputData(it) })
        viewModel.responseUpdate.observe(this, Observer { updateData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        showToast(it?.message.toString())
    }

    private fun updateData(it: ResponseAction?) {
        showToast("Data berhasil diupdate")
        finish()
    }

    private fun inputData(it: ResponseAction?) {
        showToast("Data berhasil disimpan")
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}