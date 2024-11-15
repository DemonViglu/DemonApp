package com.demonviglu.demonapp.demon_notification
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.demonviglu.demonapp.R
import com.demonviglu.demonapp.main_activity.MainActivity
import com.demonviglu.demonapp.util.PermissionManager
import kotlin.contracts.contract
import kotlin.coroutines.coroutineContext


class DemonNotificationManager(iactivity:Context) {

    private val activity : Context = iactivity

    var ChannelID : String = "Demon"
    var ChannelName : CharSequence = "DEFAULT_CHANNEL_NAME"
    var ChannelDescription : String = "DEFAULT_CHANNEL_DESCRIPTION"
    var Importance : Int = NotificationManager.IMPORTANCE_HIGH
    var NotificationGroup : String = "DING~"
    var NotificationID : Int = 5238

    var NC : NotificationChannel = NotificationChannel(ChannelID,ChannelName,Importance)
    var NM : NotificationManager = activity.getSystemService(NotificationManager::class.java)

    var PM : PermissionManager = PermissionManager(activity)

    init {
        NC.description = ChannelDescription
        NM.createNotificationChannel(NC)
    }

    fun SendNotification(content:String = "TextContent:\n",title:String = "ContentTitle"){
        PM.AutoAskForPermission(android.Manifest.permission.POST_NOTIFICATIONS)
        if(PM.HasPermission(android.Manifest.permission.POST_NOTIFICATIONS)){

            //Create a Notification
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(activity, ChannelID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(BuildNavBackIntent())
                .setContentText(content)
                .setAutoCancel(true)
                .setGroup(NotificationGroup)
                .setGroupSummary(false)
                .setSound(RingtoneManager.getActualDefaultRingtoneUri(activity, RingtoneManager.TYPE_RINGTONE))
                .setPriority(
                    NotificationCompat.PRIORITY_HIGH
                )
            NM.notify(NotificationID++,builder.build())
        }
    }

    private fun BuildNavBackIntent():PendingIntent{
        // Create an Intent for the activity you want to start.
        val resultIntent: Intent = Intent(activity, MainActivity::class.java)
        val stackBuilder : TaskStackBuilder = TaskStackBuilder.create(activity)
        stackBuilder.addNextIntentWithParentStack(resultIntent)

        return stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }
}