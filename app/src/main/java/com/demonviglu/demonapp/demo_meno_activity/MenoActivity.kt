package com.demonviglu.demonapp.demo_meno_activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.demonviglu.demonapp.R
import com.demonviglu.demonapp.ui.component.MenoListviewItem

class MenoActivity : Activity() {

    lateinit var btn : Button
    lateinit var lv : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.demo_meno_layout)

        awake()

        setAddMenoNoteBtn()

    }

    private fun awake(){
        lv = findViewById(R.id.meno_listview)
    }

    private fun setAddMenoNoteBtn(){
        btn = findViewById(R.id.add_meno_note_btn)

        btn.setOnClickListener{
            createMenoListviewItem()
        }
    }

    fun createMenoListviewItem(){
        val a = MenoListviewItem(this)
        lv.addView(a)
    }

}