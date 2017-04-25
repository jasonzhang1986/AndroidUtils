package com.leplay.androidutils.base

import android.app.Application
import com.leplay.android.utils.Utils

/**
 * Created by JifengZhang on 2017/4/24.
 */
class TestApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Utils.initialize(this)
    }
}