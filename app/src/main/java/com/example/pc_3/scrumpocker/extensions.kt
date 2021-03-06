package com.example.pc_3.scrumpocker

import android.content.Context
import android.preference.PreferenceManager
import android.widget.Toast


/**
 * Created by PC-3 on 16/09/2017.
 */
private val DECK_TYPE: String = "DECK TYPE"
private val HIDE_DECK_SETTING: String = "HIDE_DECK_SETTING"

fun Context.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

fun Context.saveDeckType(type: String) {
    val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
    editor.putString(DECK_TYPE, type).apply()
}

fun Context.saveHideDeckSettings(hide: Boolean) {
    val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
    editor.putBoolean(HIDE_DECK_SETTING, hide).apply()
}

fun Context.getDeckType(): String = PreferenceManager.getDefaultSharedPreferences(this).getString(DECK_TYPE, "")

fun Context.getHideDeckSetting(): Boolean = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(HIDE_DECK_SETTING, true)