package com.hanief.mahasiswaappmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanief.mahasiswaappmvvm.R
import com.hanief.mahasiswaappmvvm.ui.model.getdata.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter(val data: List<DataItem>?, val itemClick: OnClickListener) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nama = view.tv_name
        val nohp = view.tv_nohp
        val alamat = view.tv_alamat
        val hapus = view.iv_delete
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.itemView.setOnClickListener {
            itemClick.detail(item)
        }

        holder.hapus.setOnClickListener {
            itemClick.delete(item)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    interface OnClickListener {
        fun detail(item: DataItem?)

        fun delete(item: DataItem?)
    }
}