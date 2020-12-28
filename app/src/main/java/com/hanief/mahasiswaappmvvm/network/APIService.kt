package com.hanief.mahasiswaappmvvm.network

import com.hanief.mahasiswaappmvvm.ui.model.action.ResponseAction
import com.hanief.mahasiswaappmvvm.ui.model.getdata.ResponseGetData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    // getData
    @GET("getData.php")
    fun getData(): Flowable<ResponseGetData>

    // getDataById
    @GET("getData.php")
    fun getDataById(@Query("id_mahasiswa") id: String): Call<ResponseGetData>

    // insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("mahasiswa_nama") nama: String,
                   @Field("mahasiswa_nohp") nohp: String,
                   @Field("mahasiswa_alamat") alamat: String): Flowable<ResponseAction>

    // update data
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id_mahasiswa") id: String,
                   @Field("mahasiswa_nama") nama: String,
                   @Field("mahasiswa_nohp") nohp: String,
                   @Field("mahasiswa_alamat") alamat: String): Flowable<ResponseAction>

    // delete data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id: String): Flowable<ResponseAction>
}