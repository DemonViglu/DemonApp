package com.demonviglu.demonapp.demo_meno_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demonviglu.demonapp.R

class EditMenoFragment :Fragment() {

    lateinit var root : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_edit_meno_layout,container, false)
        awake()

        return root
    }

    private fun awake(){
        println("Try to open fragment")



    }

}