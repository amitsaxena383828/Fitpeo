package com.assignment.fitpeo.interfaces

import com.assignment.fitpeo.model.ApiResponseData
import retrofit2.Call
import retrofit2.http.GET

interface SearchApi {
    @GET(".")
    fun getMovies(): Call<ApiResponseData>
}