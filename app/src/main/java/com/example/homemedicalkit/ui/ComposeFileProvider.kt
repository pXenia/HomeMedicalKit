package com.example.homemedicalkit.ui

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.homemedicalkit.R
import java.io.File

class ComposeFileProvider : FileProvider(
    R.xml.files
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.getFilesDir(), "images_kit")
            if (!directory.exists()) {directory.mkdir()}
            val file = File(directory,
                "JPEG_${System.currentTimeMillis()}.jpg"
            )
            file.createNewFile()
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}