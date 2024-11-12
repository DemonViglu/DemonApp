package com.demonviglu.demonapp.ui.component
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.demonviglu.demonapp.R

class ActivityTeleport:ConstraintLayout {

    constructor(mcontext: Context):super(mcontext){
        initView(this.context)
    }

    constructor(mcontext: Context,mattri:AttributeSet):super(mcontext,mattri){
        initView(this.context)
    }

    constructor(mcontext: Context,mattri:AttributeSet,defStyAttri:Int):super(mcontext,mattri,defStyAttri){
        initView(this.context)
    }

    private fun initView(context: Context){
        val view = LayoutInflater.from(context).inflate(R.layout.component_activity_teleport_layout,this,true)

        btn = view.findViewById(R.id.activity_teleport_btn)
        titleText = view.findViewById(R.id.activity_teleport_title_text)
        contentText = view.findViewById(R.id.activity_teleport_content_text)

        awake()
    }

    lateinit var btn_on_click_listener : ()->Unit

    lateinit var btn:Button
    lateinit var titleText:TextView
    lateinit var contentText:TextView

    public fun setOnButtonClickListener(callback:()->Unit){
        btn_on_click_listener = callback;
    }

    public fun setTitleText(s:String){
        titleText.text = s
    }

    public fun setContentText(s:String){
        contentText.text = s
    }

    public fun setButtonText(s:String){
        btn.text = s
    }

    private fun awake(){

        btn.setOnClickListener{
            btn_on_click_listener.invoke()
        }

    }

}