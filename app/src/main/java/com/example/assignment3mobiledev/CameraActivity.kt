package com.example.assignment3mobiledev

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.io.File
class CameraActivity : ComponentActivity() {
    private lateinit var photoFile: File
    private lateinit var photoUri: Uri
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {
                    takenImageUriState?.value = photoUri
                }
            }
        setContent {
            CameraScreen(
                onOpenCamera = {
                    photoFile = createImageFile()
                    photoUri = FileProvider.getUriForFile(
                        this, "${packageName}.fileprovider", photoFile
                    )
                    takePictureLauncher.launch(photoUri)
                })
        }
    }
    companion object {
        var takenImageUriState: MutableState<Uri?>? = null
    }
    private fun createImageFile(): File {
        val dir = File(cacheDir, "images").apply { mkdirs() }
        return File.createTempFile("captured_", ".jpg", dir)
    }
}
@Composable
fun CameraScreen(onOpenCamera: () -> Unit) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    CameraActivity.takenImageUriState = imageUri
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onOpenCamera) {
            Text("Open Camera")
        }
        Spacer(modifier = Modifier.height(20.dp))
        imageUri.value?.let { uri ->
            AndroidView(factory = { context ->
                ImageView(context).apply { adjustViewBounds = true }
            }, modifier = Modifier.size(300.dp), update = { imageView ->
                Glide.with(imageView.context).load(uri).into(imageView)
            })
        }
    }
}
