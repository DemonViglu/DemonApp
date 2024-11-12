package com.demonviglu.demonapp.demo_meno_activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.demonviglu.demonapp.R

class MenoActivity : Activity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.demo_meno_layout)

        setAddMenoNoteBtn()
    }

    private fun setAddMenoNoteBtn(){
        btn = this.findViewById(R.id.edit_meno_submit_btn)

        btn.setOnClickListener{

        }
    }

}