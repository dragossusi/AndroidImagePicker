package ro.dragossusi.android.imagepicker.wrapper

import android.Manifest
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import ro.dragossusi.android.imagepicker.ImagePickerListener
import ro.dragossusi.android.imagepicker.createPrivateImageFile
import ro.dragossusi.android.imagepicker.ui.ImagePicker


/**
 *
 * @since 9/21/20
 * @author dragos
 */
class ActivityImagePickerWrapper constructor(
    private val activity: AppCompatActivity,
    private val providerAuthority: String,
    var onPermissionError: ((String) -> Unit)? = null,
    var onImageSelected: (Uri?) -> Unit
) : ImagePickerListener {

    private var uri: Uri? = null

    private val imagePicker: ActivityResultLauncher<Uri> =
        activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) onImageSelected(uri)
        }

    private val requestPermissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            //start image capture
            imagePicker.launch(uri)
        } else {
             /* Explain to the user that the feature is unavailable because the
             features requires a permission that the user has denied. At the
             same time, respect the user's decision. Don't link to system
             settings in an effort to convince the user to change their
             decision. */
            onPermissionError?.invoke(Manifest.permission.CAMERA)
        }
    }

    private val galleryPicker: ActivityResultLauncher<String> =
        activity.registerForActivityResult(ActivityResultContracts.GetContent()) {
            onImageSelected(it)
        }

    fun pickImage(uri: Uri) {
        this.uri = uri
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    fun pickGalleryImage() {
        galleryPicker.launch("image/*")
    }

    fun showDialog() {
        ImagePicker.createDialog(activity, this)
            .show()
    }

    override fun onCameraPicker() {
        val file = activity.createPrivateImageFile()
        val uri = FileProvider.getUriForFile(
            activity,
            providerAuthority,
            file
        )
        if (uri != null) pickImage(uri)
    }

    override fun onGalleryPicker() {
        pickGalleryImage()
    }

}