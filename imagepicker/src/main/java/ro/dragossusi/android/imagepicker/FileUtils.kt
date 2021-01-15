package ro.dragossusi.android.imagepicker

import android.content.Context
import android.net.Uri
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @since 9/18/20
 * @author dragos
 */

fun generateTimeStamp(): String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(
    Date()
)

internal fun createTempFile(storageDir: File, prefix: String, extension: String): File {
    val timeStamp: String = generateTimeStamp()
    val imageFileName = "${prefix}_${timeStamp}"
    val dirExists = storageDir.createFolder()
    if (!dirExists) {
        throw IOException("Cannot create folder $storageDir")
    }
    return File.createTempFile(
        imageFileName,  /* prefix */
        extension,  /* suffix */
        storageDir /* directory */
    )
}

@Throws(IOException::class)
fun Context.createPrivateImageFile(): File {
    // Create an image file name
    return createTempFile(this.imageDirectory, "JPEG", ".jpg")
}

/**
 * copy an image from an uri to a private file
 * @param context       any context
 * @param uri           uri of the file to be copied
 * @param storageDir    folder where to save image
 * @param prefix        file prefix
 * @param extension     file extension
 */
fun Context.copyMedia(uri: Uri, photoFile: File): File? {

    val inputStream: InputStream = contentResolver.openInputStream(uri)!!.buffered()
    inputStream.use {
        FileOutputStream(photoFile).buffered().use { out ->
            it.copyTo(out)
        }
    }

    // Copying
    return photoFile
}

fun Context.copyImage(uri: Uri): File? {
    val photoFile: File
    try {
        photoFile = createPrivateImageFile()
        return copyMedia(
            uri,
            photoFile
        )
    } catch (e: Exception) {
        Timber.e(e)
        return null
    }
}

fun File.createFolder(): Boolean {
    var dirExists = exists()
    if (!dirExists) {
        dirExists = mkdir()
    }
    return dirExists
}