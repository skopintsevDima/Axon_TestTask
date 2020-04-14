package com.test.axontest.util

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun showBottomMsg(
    rootView: View,
    @StringRes messageStrId: Int,
    @StringRes actionStrId: Int? = null,
    actionListener: ((View) -> Unit)? = null
) {
    Snackbar.make(rootView, messageStrId, Snackbar.LENGTH_LONG)
        .also { snackbar ->
            actionStrId?.let { snackbar.setAction(actionStrId, actionListener) }
        }.show()
}