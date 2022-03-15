package com.ansela.notesapp.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(activity:Activity){
    val inpuMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusView = activity.currentFocus
    currentFocusView.let {
        inpuMethodManager.hideSoftInputFromWindow(
            currentFocusView?.windowToken,InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}