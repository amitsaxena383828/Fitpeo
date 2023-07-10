package com.assignment.fitpeo.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.fitpeo.adapter.ImageListAdapter
import com.assignment.fitpeo.databinding.ActivityMainBinding
import com.assignment.fitpeo.model.ApiResponseData
import com.assignment.fitpeo.retrofitHelper.RequestResult
import com.assignment.fitpeo.utility.Utility
import com.assignment.fitpeo.viewmodel.ImageViewModel

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var imageViewModel: ImageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        if(Utility.isInternetAvailable(this))
            hitApi()
        else
            Utility.showNoInternetDialog(this)
    }


    private fun hitApi() {
        imageViewModel = ViewModelProvider(this)[ImageViewModel::class.java]
        imageViewModel.getData()
            .observe(this) { response ->
                if (response.status == RequestResult.Status.SUCCESS) {
                    val imageData = response.data as ApiResponseData
                    setupRecyclerView(imageData)
                } else {
                    Toast.makeText(
                        this,
                        "Error while retrieving data.\n\n\n\nResponse==\n\n\n" + response.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

    private fun setupRecyclerView(imageData: ApiResponseData) {
        activityMainBinding.rvImage.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ImageListAdapter(this, imageData)
        activityMainBinding.rvImage.adapter = adapter
        activityMainBinding.progressBar.visibility = View.GONE
    }
}