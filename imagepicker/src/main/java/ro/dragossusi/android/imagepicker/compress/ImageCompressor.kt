package ro.dragossusi.android.imagepicker.compress

import android.content.Context
import android.net.Uri
import androidx.annotation.WorkerThread
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import ro.dragossusi.android.imagepicker.compress.CompressedImage
import ro.dragossusi.android.imagepicker.copyImage


/**
 *
 * @since 9/18/20
 * @author dragos
 */
object ImageCompressor {

    @WorkerThread
    suspend fun compressImage(context: Context, uri: Uri): CompressedImage? {
        val galleryFile = context.copyImage(uri) ?: return null
        System.gc()
        return CompressedImage(
            uri,
            Compressor.compress(
                context,
                galleryFile
            ) {
                resolution(2000, 2000)
                size(1_048_576L)
//                quality(81)
            }
        )
    }
}