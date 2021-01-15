# Android Image Picker

New Android versions support

## Getting started

### Declare your target directory

You can declare your target directory using code or string resource.

For code, initialise the ImagePicker directory(usually in Application class):

```kotlin
ImagePicker.directory = "YourDirectory"
```

For String resource:

```xml
<string name="image_picker_folder_name">YourDirectory</string>
```

### Create the ImageWrapper

The library comes with a wrapper for fragments:

```kotlin
    private val imagePickWrapper = FragmentImagePickerWrapper(
        this,
        "${BuildConfig.APPLICATION_ID}.filesystem.provider",
        ::onPermissionError
    ) { uri:Uri?->
        //todo what you want with the image
    }

    private fun onPermissionError(permission: String) {
        //todo permission error handling
        AlertDialog.Builder(requireContext())
            .setTitle("Your permission error")
            .setMessage(permission)
            .show()
    }

```

### Use builtin AvatarImageView

Add the AvatarImageView in the layout xml

```xml
<ro.dragossusi.android.imagepicker.ui.AvatarImageView
    android:id="@+id/image_avatar"
    android:layout_width="120dp"
    android:layout_height="120dp"
    android:background="?selectableItemBackgroundBorderless"
    android:clickable="true"
    android:focusable="true"
    tools:src="@tools:sample/avatars" />
```

Set the wrapper to the view

```kotlin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //auto sets onClickListener
        findViewById<AvatarImageView>(R.id.image_avatar)
            .imagePickerWrapper = imagePickWrapper
    }

```



