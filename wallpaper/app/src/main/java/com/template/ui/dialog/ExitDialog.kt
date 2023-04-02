package com.template.ui.dialog

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import com.template.databinding.ExitDialogBinding
import com.template.domain.GetListWallpapers
import com.template.ui.MainActivity
import com.template.ui.PreviewActivity

object ExitDialog {

    fun showDialog(activity: MainActivity) {
        var dialog: AlertDialog? = null
        val listWallpaper = GetListWallpapers.getWallpapersList(activity).random()
        val binding = ExitDialogBinding.inflate(LayoutInflater.from(activity))
        val builder = AlertDialog.Builder(activity).setView(binding.root)

        binding.apply {

            wallpaper.setImageResource(listWallpaper.imageUrl)

            buttonCloseApp.setOnClickListener{
                activity.finish()
            }

            buttonSetWallpaper.setOnClickListener {
                val intent = PreviewActivity.newIntentWallpaper(
                    activity, listWallpaper.imageUrl, listWallpaper.name
                )
                startActivity(activity, intent, null)
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