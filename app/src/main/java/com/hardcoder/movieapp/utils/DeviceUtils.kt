package com.hardcoder.movieapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class DeviceUtils {
    companion object {
        fun openKeyboard(activity: Activity, editText: EditText?) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }

        fun closeKeyboard(activity: Activity?) {
            activity?.let {
                val inputMethodManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
            }
        }
    }
}