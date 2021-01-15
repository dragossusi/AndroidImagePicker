package ro.dragossusi.android.imagepicker

import android.content.Context
import android.os.Environment
import java.io.File


/**
 *
 * @since 9/18/20
 * @author dragos
 */
const val FOLDER_IMAGES = "Images"

val Context.imageDirectory: File
    get() = File(
        getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!,
        FOLDER_IMAGES
    )