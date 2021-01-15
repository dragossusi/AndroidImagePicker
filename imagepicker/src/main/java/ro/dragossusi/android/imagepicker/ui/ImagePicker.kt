package ro.dragossusi.android.imagepicker.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import ro.dragossusi.android.imagepicker.R
import ro.dragossusi.android.imagepicker.ImagePickerListener


/**
 *
 * @since 9/21/20
 * @author dragos
 */
object ImagePicker {

    fun createDialog(
        context: Context,
        listener: ImagePickerListener
    ): BottomSheetDialog {
        val alertDialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.alert_image_picker, null, false)
        view.findViewById<View>(R.id.text_camera).setOnClickListener {
            listener.onCameraPicker()
            alertDialog.dismiss()
        }
        view.findViewById<View>(R.id.text_gallery).setOnClickListener {
            listener.onGalleryPicker()
            alertDialog.dismiss()
        }
        alertDialog.setContentView(view)
        return alertDialog
    }

}