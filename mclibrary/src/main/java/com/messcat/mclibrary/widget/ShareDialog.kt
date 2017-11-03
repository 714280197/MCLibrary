package com.messcat.mclibrary.widget

import android.app.Dialog
import android.content.Context
import android.view.Window

import com.messcat.mclibrary.R

/**
 * Created by Administrator on 2017/9/28 0028.
 */

class ShareDialog(context: Context, gravity: Int) {

    private val shareDialog: Dialog
    private val window: Window?

    init {
        shareDialog = Dialog(context, R.style.dialog)
        window = shareDialog.window
        window!!.setContentView(R.layout.dialog_shape)
        window.setGravity(gravity)
    }

    fun showSearchDialog() {
        shareDialog.show()
    }

    fun disSearchDialog() {
        shareDialog.dismiss()
    }
}
