package com.arif.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("/posts")
    fun getallPosts() : Call<List<Posts>>
}