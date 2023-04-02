package com.template.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.template.BuildConfig
import com.template.R
import com.template.databinding.AboutUsDialogBinding

object AboutUsDialog {

    fun showDialog(context: Context) {
        var dialog: AlertDialog? = null
        val version = BuildConfig.VERSION_CODE
        val binding = AboutUsDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context).setView(binding.root)

        binding.apply {
            tvAppName.setText(R.string.app_name)

            tvVersion.text = String.format(
                context.getString(R.string.version_1),
                version.toString()
            )

            buttonClose.setOnClickListener{

                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.apply {
            window?.setBackgroundDrawable(null)
            show()
        }
    }
}