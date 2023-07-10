package com.assignment.fitpeo.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.fitpeo.databinding.ActivityDetailBinding
import com.assignment.fitpeo.model.ApiResponseDataItem
import com.assignment.fitpeo.utility.Constants
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var imageData: ApiResponseDataItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)
        getData()
        setData()
    }

    fun getData(){
        val jsonString = intent.getStringExtra(Constants.EXTRA_PARAM_IMAGE_DETAIL)
        imageData =
            FitpeoApplication.getGsonInstance().fromJson(jsonString, ApiResponseDataItem::class.java)
        activityDetailBinding.imageDetail=imageData
    }

    fun setData() {
        Picasso.get().load(imageData.url).into(activityDetailBinding.ivThumnail)
    }
}