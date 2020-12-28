package com.hanief.mahasiswaappmvvm.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hanief.mahasiswaappmvvm.R
import com.hanief.mahasiswaappmvvm.ui.adapter.DataAdapter
import com.hanief.mahasiswaappmvvm.ui.model.getdata.DataItem
import com.hanief.mahasiswaappmvvm.ui.model.getdata.ResponseGetData
import com.hanief.mahasiswaappmvvm.ui.viewmodel.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModelMainActivity::class.java)

        faButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
        attachObserve()

    }

    private fun attachObserve() {
        viewModel.responseData.observe(this, { showData(it) })
        viewModel.isError.observe(this, { showError(it) })
        viewModel.isLoading.observe(this, { showLoading(it) })
    }

    private fun showLoading(it: Boolean?) { if (it == true) pgBar.visibility = View.VISIBLE else pgBar.visibility = View.GONE
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showData(it: ResponseGetData) {

        val adapter = DataAdapter(it.data, object : DataAdapter.OnClickListener {

            override fun detail(item: DataItem?) {
                val intent = Intent(applicationContext, InputActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent) }
             override fun delete(item: DataItem?) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus Data")
                    setMessage("Yakin mau hapus data")
                    setPositiveButton("Hapus") { dialog, which ->
                        viewModel.deleteData(item?.idMahasiswa ?: "")
                        dialog.dismiss()
                    }
                    setNegativeButton("cancel") { dialog, i ->
                        dialog.dismiss()
                    }
                }.show()
            }
        })

        rv_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListData()
    }
}