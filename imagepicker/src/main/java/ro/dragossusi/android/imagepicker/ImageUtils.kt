package ro.dragossusi.android.imagepicker

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.io.File


/**
 *
 * @since 9/18/20
 * @author dragos
 */

@RequiresApi(Build.VERSION_CODES.Q)
fun ContentResolver.createMediaUri(isVideo: Boolean, pending: Boolean): Uri? {
    //set image/video constants
    val externalUri = if (isVideo) MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    else MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    //create ContentValues
    val values = ContentValues()
    values.put(
        MediaStore.Images.Media.RELATIVE_PATH,
        getEnvironmentFolder()
    )
    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
    values.put(MediaStore.Images.Media.IS_PENDING, true)
    //create uri
    return insert(externalUri, values)
}

@RequiresApi(Build.VERSION_CODES.Q)
private fun Context.addMediaToGallery(uri: Uri, isVideo: Boolean): Boolean {
    val resolver = contentResolver
    //create uri
    val destination = resolver.createMediaUri(isVideo, true)
    if (destination != null) {
        try {
            if (contentResolver.copyUri(uri, destination)) {
                val values = ContentValues()
                values.put(MediaStore.MediaColumns.IS_PENDING, false)
                resolver.update(destination, values, null, null)
                return true
            }
        } catch (e: Exception) {
            resolver.delete(destination, null, null)
            throw e
        }
    } else {
        Timber.e(Exception("uri is null"))
    }
    return false
}

private fun getEnvironmentFolder(): String {
    return Environment.DIRECTORY_PICTURES + File.separator + "eSend"
}

fun ContentResolver.copyUri(
    from: Uri,
    to: Uri
): Boolean {
    openInputStream(from)?.use { source ->
        openOutputStream(to)?.use { dest ->
            source.copyTo(dest)
            return true
        }
    }
    return false
}