package com.yuyan

import android.app.Application
import com.yuyan.imemodule.application.Launcher

/**
 * Applicaiton入口
 * @since 2019/6/18
 */
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        val t = android.os.SystemClock.elapsedRealtime()
        Launcher.instance.initData(baseContext)
        android.util.Log.e("YuyanImeDual", "App.onCreate initData done +" + (android.os.SystemClock.elapsedRealtime() - t) + "ms")
    }
}
