package com.demonviglu.demonapp.main_activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.demonviglu.demonapp.R

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_DemonApp)
        setContentView(R.layout.main_home_layout)


        setWelcomeBtn()
    }

    private fun setWelcomeBtn(){
        val btn : Button = findViewById(R.id.main_home_welcome_btn)
        btn.setOnClickListener{
            val i = Intent("open_demo_one_action")
            startActivity(i)
        }
    }
}