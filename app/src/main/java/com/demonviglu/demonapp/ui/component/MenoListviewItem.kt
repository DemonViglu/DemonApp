package com.demonviglu.demonapp.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.demonviglu.demonapp.R


class MenoListviewItem :ConstraintLayout {

    val meno: Array<String> = arrayOf("NotBegin", "Doing", "Finished")

    constructor(mcontext: Context) : super(mcontext) {
        initView(this.context)
    }

    constructor(mcontext: Context, mattri: AttributeSet) : super(mcontext, mattri) {
        initView(this.context)
    }

    constructor(mcontext: Context, mattri: AttributeSet, defStyAttri: Int) : super(
        mcontext,
        mattri,
        defStyAttri
    ) {
        initView(this.context)
    }

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.component_meno_layout, this, true)

        val spinner: Spinner = view.findViewById(R.id.meno_spinner)

        spinner.adapter = MenoAdapter(meno,context)
    }

}

class MenoAdapter : BaseAdapter{

    private lateinit var mstates : Array<String>
    private lateinit var mcontext: Context

    constructor( states:Array<String>,context:Context){
        mstates = states
        mcontext = context
    }

    override fun getCount(): Int {
        return mstates.size
    }

    override fun getItem(position: Int): Any {
        return mstates.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val _LayoutInflater = LayoutInflater.from(mcontext)
        val view : View = _LayoutInflater.inflate(R.layout.meno_spinner_layout, null)

        if(view!=null){
            view.findViewById<TextView>(R.id.meno_spinner_text).setText(mstates.get(position))
        }

        return view
    }


}