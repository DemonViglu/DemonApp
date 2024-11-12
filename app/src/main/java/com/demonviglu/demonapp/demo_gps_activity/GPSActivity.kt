package com.demonviglu.demonapp.demo_gps_activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.demonviglu.demonapp.R

class GPSActivity:Activity(),LocationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gps_usage)

        tvA  = findViewById(R.id.gps_latitude_tv)
        tvB  = findViewById(R.id.gps_longitude_tv)
        tvAdd = findViewById(R.id.gps_nowAddress_tv)

        // 判断当前是否拥有使用GPS的权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // 申请权限
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 100);
        }

        // 获取当前位置管理器
        val locationManager = (this.getSystemService(LOCATION_SERVICE))as LocationManager
        // 启动位置请求
        // LocationManager.GPS_PROVIDER GPS定位
        // LocationManager.NETWORK_PROVIDER 网络定位
        // LocationManager.PASSIVE_PROVIDER 被动接受定位信息
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0f, this);

        Toast.makeText(this,locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER).toString(),Toast.LENGTH_SHORT).show()
    }

    lateinit var tvA : TextView
    lateinit var tvB : TextView
    lateinit var tvAdd:TextView


    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {
        println("Caught a change")
        tvA.text = "纬度： "+location.latitude.toString()
        tvB.text = "经度： "+location.longitude.toString()
        tvAdd.text = "海拔： "+location.altitude.toString()
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
        Toast.makeText(this,"enable",Toast.LENGTH_SHORT).show()
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
        Toast.makeText(this,"disnable",Toast.LENGTH_SHORT).show()
    }
}