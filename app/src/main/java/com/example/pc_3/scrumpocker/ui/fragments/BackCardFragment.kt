package com.example.pc_3.scrumpocker.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.pc_3.scrumpocker.R

/**
 * Created by PC-3 on 17/09/2017.
 */
class BackCardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_card_back, container, false)
    }
}