package com.demonviglu.demonapp.util
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat


class PermissionManager(iactivity: Activity, icontext: Context) {
    private val activity = iactivity
    private val context = icontext

    public val PermissionRequestCode : Int = 0

    fun AutoAskForPermission(permission: String){
        if(!HasPermission(permission)){

            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)){

                ActivityCompat.requestPermissions(activity,Array<String>(1){permission},PermissionRequestCode)

            }
            else {

                ShowPermissionSettingDialog()

            }

        }
    }

    fun HasPermission(permission: String):Boolean{
        return context.checkSelfPermission(permission) != PackageManager.PERMISSION_DENIED
    }

    fun ShowPermissionSettingDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Ask for right")
        builder.setMessage("Why you refuse me T-T")
        builder.setPositiveButton("GO",
            DialogInterface.OnClickListener { dialog, which ->
                val intent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.setData(uri)
                activity.startActivity(intent)
            })
        builder.setNegativeButton("QAQ", null)
        builder.show()
    }
}