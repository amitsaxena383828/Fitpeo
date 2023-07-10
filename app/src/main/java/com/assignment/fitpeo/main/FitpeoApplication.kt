package com.assignment.fitpeo.main

import android.app.Application
import com.google.gson.Gson

class FitpeoApplication: Application() {

    companion object {
        private lateinit var gson: Gson

        private lateinit var instance: FitpeoApplication

        @JvmStatic
        fun getInstance() = instance

        @JvmStatic
        fun getGsonInstance() = gson
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        gson = Gson()
    }
}