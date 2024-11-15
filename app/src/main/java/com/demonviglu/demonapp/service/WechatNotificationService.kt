package com.demonviglu.demonapp.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.demonviglu.demonapp.R
import com.demonviglu.demonapp.demon_notification.DemonNotificationManager


class WechatNotificationService : NotificationListenerService() {

    var s :  String? = ""
    private lateinit var nameArr : List<String>

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO Auto-generated method stub
        s = intent?.getStringExtra("Names")
        if(s!=null){
            nameArr = s!!.split(",")
        }

        val noti: Notification = Notification.Builder(this,"Demon")
            .setContentTitle("Demon App")
            .setContentText("服务监听当中")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(123456, noti)
        return Service.START_STICKY
    }
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if(sbn!=null){
            handleWechatNotification(sbn)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }

    private fun handleWechatNotification(sbn:StatusBarNotification){
       if(sbn.packageName == "com.tencent.mm"){
            val notification = sbn.notification
            if(notification!=null){
                val extras = notification.extras
                val title = extras.getString(Notification.EXTRA_TITLE)
                val text = extras.getString(Notification.EXTRA_TEXT)
                val n = DemonNotificationManager(this)
                if(nameArr.contains(title)){
                    n.SendNotification("您的特别关心来信")
                }
            }
        }
    }
}