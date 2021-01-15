package ro.dragossusi.android.imagepicker.compress

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

/**
 *
 * @author Dragos
 * @since 25.03.2020
 */
@Parcelize
data class CompressedImage(
    val original: Uri,
    val compressed: File
):Parcelable