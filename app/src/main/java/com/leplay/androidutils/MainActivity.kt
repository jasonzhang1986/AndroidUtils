package com.leplay.androidutils

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.leplay.androidutils.test.TestNetworkActivity
import launchActivity

/**
 * Created by JifengZhang on 2017/4/24.
 */
class MainActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.btn_network).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_network -> launchActivity<TestNetworkActivity>()
        }
    }
}