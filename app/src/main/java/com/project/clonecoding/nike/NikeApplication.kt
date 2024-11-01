package com.project.clonecoding.nike

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NikeApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
    }
}