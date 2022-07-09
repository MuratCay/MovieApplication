package com.hardcoder.movieapp.ui.base

import android.widget.EditText
import androidx.fragment.app.Fragment
import com.hardcoder.movieapp.utils.DeviceUtils

abstract class AbstractLoginFragment : Fragment() {

    fun openKeyboard(editText: EditText) {
        DeviceUtils.openKeyboard(requireActivity(), editText)
    }

    fun closeKeyboard() {
        DeviceUtils.closeKeyboard(requireActivity())
    }
}