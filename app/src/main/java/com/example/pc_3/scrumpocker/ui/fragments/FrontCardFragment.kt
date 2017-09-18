package com.example.pc_3.scrumpocker.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pc_3.scrumpocker.R

/**
 * Created by PC-3 on 17/09/2017.
 */
class FrontCardFragment : Fragment() {
    lateinit var tvValue: TextView
    var mValue = " "
    var mType = " "
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_card_front, container, false)
        mValue = arguments.getString("value")
        mType = arguments.getString("type")
        linkUI(v)
        init()
        return v
    }

    private fun linkUI(v: View?) {
        tvValue = v?.findViewById(R.id.tvValue) as TextView
    }

    private fun init() {
        tvValue.text =
                when (mType) {
                    "number" -> mValue
                    "infinite" -> "8"
                    "break" -> "B"
                    else -> "?"
                }
    }
}