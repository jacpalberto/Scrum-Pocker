package com.example.pc_3.scrumpocker.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pc_3.scrumpocker.R

/**
 * Created by PC-3 on 17/09/2017.
 */
class FrontCardFragment : Fragment() {
    lateinit var ivFront: ImageView
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
        ivFront = v?.findViewById(R.id.ivFront) as ImageView
    }

    private fun init() {
        var fileName: String = when (mValue) {
            "0", "1", "2", "3", "5", "8", "13", "20", "21", "34", "40", "55", "80", "89"
            -> "drawable/card_${mValue}"
            "1/2" -> "drawable/card_half"
            else -> "drawable/card_${mValue}"
        }
        if (mType.equals("unknown")) fileName = "drawable/card_q"
        if (mType.equals("break")) fileName = "drawable/card_coffee"
        if (mType.equals("infinite")) fileName = "drawable/infinite"
        val id = activity.resources.getIdentifier(fileName, null, activity.packageName)
        ivFront.setImageResource(id)
    }
}