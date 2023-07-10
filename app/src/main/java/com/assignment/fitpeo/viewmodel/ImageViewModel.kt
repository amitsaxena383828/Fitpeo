package com.assignment.fitpeo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.fitpeo.interfaces.SearchApi
import com.assignment.fitpeo.model.ApiResponseData
import com.assignment.fitpeo.retrofitHelper.RequestResult
import com.assignment.fitpeo.retrofitHelper.RetrofitHelper
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel : ViewModel() {
    var stringMutableLiveData = MutableLiveData<RequestResult<Any?>>()
    fun getData(): LiveData<RequestResult<Any?>> {
        stringMutableLiveData = MutableLiveData()
        loadData()
        return stringMutableLiveData
    }

    private fun loadData() {
        viewModelScope.launch {
            val call =
                RetrofitHelper.getInstance().create(SearchApi::class.java)
                    .getMovies()
            call.enqueue(object : Callback<ApiResponseData?> {
                override fun onFailure(call: Call<ApiResponseData?>, t: Throwable) {
                    Log.e("Amit", t.message, t)
                }

                override fun onResponse(
                    call: Call<ApiResponseData?>,
                    response: Response<ApiResponseData?>
                ) {
                    if (response.isSuccessful) {
                        Log.i("Amit", "Loaded.")
                        stringMutableLiveData.postValue(RequestResult.success(response.body()))
                    } else {
                        Log.e("Amit", "Error: ${response.code()} ${response.message()}")
                        stringMutableLiveData.postValue(RequestResult.error(response.message() + "\t" + response.code()))
                    }
                }
            })
        }
    }


}