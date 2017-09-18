package com.example.pc_3.scrumpocker.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.widget.Button
import com.example.pc_3.scrumpocker.R
import com.example.pc_3.scrumpocker.getHideDeckSetting
import com.example.pc_3.scrumpocker.saveHideDeckSettings

/**
 * Created by PC-3 on 17/09/2017.
 */

class SettingsDialog : AppCompatDialogFragment() {
    lateinit var btnAccept: Button
    lateinit var btnCancel: Button
    lateinit var swHideCard: SwitchCompat
    var bHideDeck = true
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity.layoutInflater
        val v = inflater.inflate(R.layout.dialog_settings, null)
        linkUI(v)
        init()
        return AlertDialog.Builder(activity).setView(v).create()
    }

    private fun linkUI(v: View) {
        btnAccept = v.findViewById(R.id.btnAccept) as Button
        btnCancel = v.findViewById(R.id.btnCancel) as Button
        swHideCard = v.findViewById(R.id.swHideCard) as SwitchCompat
    }

    private fun init() {
        bHideDeck = activity.getHideDeckSetting()
        btnAccept.setOnClickListener { updateSettings() }
        btnCancel.setOnClickListener { dismiss() }
        swHideCard.isChecked = activity.getHideDeckSetting()
    }

    private fun updateSettings() {
        activity.saveHideDeckSettings(swHideCard.isChecked)
        dismiss()
    }
}