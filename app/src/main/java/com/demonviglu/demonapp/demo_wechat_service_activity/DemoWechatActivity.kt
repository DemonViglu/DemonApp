package com.demonviglu.demonapp.demo_wechat_service_activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.demonviglu.demonapp.R
import com.demonviglu.demonapp.demon_notification.DemonNotificationManager
import com.demonviglu.demonapp.service.WechatNotificationService
import com.demonviglu.demonapp.util.PermissionManager

class DemoWechatActivity :Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_wechat_layout)
        init()
    }

    private fun init(){
        findViewById<Button>(R.id.open_wechat_service_button).setOnClickListener {
            val pm = PermissionManager(this)

            pm.AutoAskForPermission(Manifest.permission.FOREGROUND_SERVICE)

            if(pm.HasPermission(Manifest.permission.FOREGROUND_SERVICE)){
                startService(Intent(this,WechatNotificationService::class.java).putExtra("Names",findViewById<EditText>(R.id.wechat_favorite_inputText).text))
            }

        }

        findViewById<Button>(R.id.close_wechat_service_button).setOnClickListener{
            stopService(Intent(this,WechatNotificationService::class.java))
        }
    }

}