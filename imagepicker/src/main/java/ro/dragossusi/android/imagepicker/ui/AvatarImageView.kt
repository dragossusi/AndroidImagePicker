package ro.dragossusi.android.imagepicker.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatImageView
import ro.dragossusi.android.imagepicker.wrapper.FragmentImagePickerWrapper
import ro.dragossusi.android.imagepicker.R


/**
 *
 * @since 9/22/20
 * @author dragos
 */
class AvatarImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.avatarImageViewStyle
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var imagePickerWrapper: FragmentImagePickerWrapper? = null

    private val onClickListener = OnClickListener {
        imagePickerWrapper?.showDialog()
    }

    init {
        setOnClickListener(onClickListener)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l ?: onClickListener)
    }

}