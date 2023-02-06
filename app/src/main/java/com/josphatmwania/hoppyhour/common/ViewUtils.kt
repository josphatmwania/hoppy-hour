package com.josphatmwania.hoppyhour.common

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

object ViewUtils {
    fun showCustomDialog(
        context: Context,
        @LayoutRes res: Int
    ): AlertDialog {
        val builder = MaterialAlertDialogBuilder(context, res)
        val dialogView = LayoutInflater.from(context).inflate(res, null)
        builder.apply {
            setView(dialogView)
            setCancelable(true)
        }
        return builder.create()
    }

    fun showErrorDialog(context: Context, message: String): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setNegativeButton(
                "dismiss"
            ) { dialog, _ -> dialog.dismiss() }
        }
        val dialog = builder.create()
        dialog.apply {
            setCancelable(true)
            setMessage(message)
        }
        return dialog
    }

    fun showSnackBar(context: Context, view: View, text: String) {
        Snackbar
            .make(context, view, text, Snackbar.LENGTH_LONG)
            .show()
    }
}