package com.demonviglu.demonapp.demo_one_activity

import android.app.Activity
import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.demonviglu.demonapp.R
import com.demonviglu.demonapp.demo_gps_activity.GPSActivity
import com.demonviglu.demonapp.demo_meno_activity.MenoActivity
import com.demonviglu.demonapp.demon_notification.DemonNotificationManager
import com.demonviglu.demonapp.ui.component.ActivityTeleport
import com.demonviglu.demonapp.util.data.NoteData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class DemoOneActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_DemonApp)
        setContentView(R.layout.demo_one_layout)

        setAddNoteBtn()
        setDebugBtn()

        createActivityList()
    }

    private fun setAddNoteBtn(){
        val btn :FloatingActionButton = findViewById(R.id.AddDemonNoteBtn)

        btn.setOnClickListener{
            test1()
        }
    }

    private fun setDebugBtn(){
        val btn : Button = findViewById(R.id.demo_one_debug_btn)
        btn.setOnClickListener{
            debug()
        }
    }

    private fun test1(){
        val ly : LinearLayout = findViewById(R.id.activity_teleport_listview)
        ly.addView(createActivityTeleport({
            val s = DemonNotificationManager(this)
            s.SendNotification("Hello!")
        }))
    }

    private fun debug(){
        val gson = Gson()
        val data = NoteData()

        if(NotificationManagerCompat.getEnabledListenerPackages(this).contains(packageName)){
            Toast.makeText(this,"Yeah",Toast.LENGTH_SHORT).show()
        }
        else{
            startActivityForResult(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 5238);
        }


        for(service in (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).getRunningServices(Int.MAX_VALUE)){
            if(service.service.className .contains("WechatNotificationService")){
                Toast.makeText(this,"Yeah 2",Toast.LENGTH_SHORT).show()
            }
        }

        Toast.makeText(this,gson.toJson(data),Toast.LENGTH_SHORT).show()

    }

    private fun createActivityList(){
        val ly : LinearLayout = findViewById(R.id.activity_teleport_listview)

        ly.addView(createActivityTeleport(
            {startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://demonviglu.world")))},
            "网页跳转器",
            "Jump to my web QAQ"
        ))

        ly.addView(createActivityTeleport(
            {startActivity(Intent(this,GPSActivity::class.java))},
            "GPS",
            "Jump to gps QAQ"
        ))

        ly.addView(createActivityTeleport(
            {startActivity(Intent(this,MenoActivity::class.java))},
            "Meno",
            "Jump to meno QAQ"
        ))

        ly.addView(createActivityTeleport(
            {startActivity(Intent("open_demo_wechat_activity_action"))},
            "Wechat",
            "Jump to wc Service QAQ"
        ))
    }

    private fun createActivityTeleport(callback:()->Unit,st:String = "title",sc:String = "content",bts:String = "GO!"):ActivityTeleport{
        val at = ActivityTeleport(this)
        at.setTitleText(st)
        at.setContentText(sc)
        at.setButtonText(bts)
        at.setOnButtonClickListener {
            callback()
        }
        return at
    }
}