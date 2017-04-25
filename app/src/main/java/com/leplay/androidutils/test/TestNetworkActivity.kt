package com.leplay.androidutils.test

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.leplay.android.utils.LogUtils
import com.leplay.android.utils.NetworkUtils
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.leplay.androidutils.R

class TestNetworkActivity : Activity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_open_wifi_setting -> NetworkUtils.openWirelessSettings()
            R.id.btn_set_wifi_enable -> {
                NetworkUtils.setWifiEnabled(!NetworkUtils.getWifiEnabled())
                handler.sendEmptyMessageDelayed(100, 500)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_network)
        findViewById(R.id.btn_open_wifi_setting).setOnClickListener(this)
        findViewById(R.id.btn_set_wifi_enable).setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        LogUtils.i("NetworkUtils isConnect = %b" , NetworkUtils.isConnected())
        LogUtils.i("NetworkUtils isAvailableByPing = %b" , NetworkUtils.isAvailableByPing())
        LogUtils.i("NetworkUtils isWifiEnable = %b" , NetworkUtils.getWifiEnabled())
        LogUtils.i("NetworkUtils isWifiConnected = %b" , NetworkUtils.isWifiConnected())
        LogUtils.i("NetworkUtils is4G = %b" , NetworkUtils.is4G())
        LogUtils.i("NetworkUtils getNetworkType = %s", NetworkUtils.getNetworkType())
    }

    private var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what) {
                100 -> {
                    LogUtils.e("NetworkUtils isWifiEnable = %b" , NetworkUtils.getWifiEnabled())
                    Toast.makeText(this@TestNetworkActivity,"wifiEnable["+ NetworkUtils.getWifiEnabled()+"]", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
