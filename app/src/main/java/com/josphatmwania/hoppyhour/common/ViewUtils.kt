package com.josphatmwania.hoppyhour.common

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object ViewUtils {
    fun showCustomDialog(
        context: Context,
        @LayoutRes res: Int
    ) : AlertDialog {
        val builder = MaterialAlertDialogBuilder(context, res)
        val dialogView = LayoutInflater.from(context).inflate(res, null)
        builder.apply {
            setView(dialogView)
            setCancelable(true)
        }
        return builder.create()
    }
}